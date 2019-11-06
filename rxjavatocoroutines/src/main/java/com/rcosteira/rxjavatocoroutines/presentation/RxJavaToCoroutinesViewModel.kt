package com.rcosteira.rxjavatocoroutines.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rcosteira.core.domain.Id
import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.extensions.addTo
import com.rcosteira.core.extensions.mapListElements
import com.rcosteira.core.interactors.CoroutineScopeUseCase.NoParameters
import com.rcosteira.core.ui.BaseViewModel
import com.rcosteira.logging.Logger
import com.rcosteira.rxjavatocoroutines.domain.usecases.*
import com.rcosteira.rxjavatocoroutines.presentation.RxJavaToCoroutinesViewEvents.DeleteUser
import com.rcosteira.rxjavatocoroutines.presentation.RxJavaToCoroutinesViewEvents.RequestUsers
import com.rcosteira.rxjavatocoroutines.presentation.entities.DisplayedDetailedUser
import com.rcosteira.rxjavatocoroutines.presentation.mappers.DisplayedDetailedUserMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RxJavaToCoroutinesViewModel @Inject constructor(
    private val getUsersFromApi: GetUsersFromApi,
    private val getUserDetailsFromApi: GetUserDetailsFromApi,
    private val getCachedUsers: GetCachedUsers,
    private val rxGetUsersFromApi: RxGetUsersFromApi,
    private val rxGetUserDetailsFromApi: RxGetUserDetailsFromApi,
    private val rxGetCachedUsers: RxGetCachedUsers,
    private val updateCachedUsers: UpdateCachedUsers,
    private val rxDeleteCachedUser: RxDeleteCachedUser,
    private val displayedDetailedUserMapper: DisplayedDetailedUserMapper,
    private val compositeDisposable: CompositeDisposable,
    private val backgroundDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    companion object {
        const val USER_LIMIT: Long = 5
    }

    val viewState: LiveData<RxJavaToCoroutinesViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<RxJavaToCoroutinesViewState> = MutableLiveData()

    init {
        _viewState.value = RxJavaToCoroutinesViewState(isLoading = true)
    }

    fun processEvents(event: RxJavaToCoroutinesViewEvents) {
        when (event) {
            is RequestUsers -> connectUIToViewState()
            is DeleteUser -> rxDeleteUser(event.id)
        }
    }

    private fun connectUIToViewState() {
        //setUserViewStateFromCacheWithRx()
        //updateCacheWithRx()
        setUserViewStateFromCacheWithCoroutines()
        updateCacheWithCoroutines()
    }

    /******************************** RxJava ********************************/

    // Gets users from the database to the view
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setUserViewStateFromCacheWithRx() {
        rxGetCachedUsers(NoParameters())
            .doOnNext { Logger.d("Database was updated. Will emit UI update.") }
            .mapListElements { displayedDetailedUserMapper.mapToUI(it) } // Extension function
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { this.handleDetailedUsers(it) },
                { this.handleErrors(it) }
            )
            .addTo(compositeDisposable) // Extension function
    }

    private fun handleDetailedUsers(detailedUsers: List<DisplayedDetailedUser>) {
        _viewState.value = RxJavaToCoroutinesViewState(
            isLoading = false,
            detailedUsers = detailedUsers
        )
    }

    // Gets users from the api and stores them in the database
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun updateCacheWithRx() {
        getUsersFromApiAsSingle()
            .doOnSuccess { Logger.d("Updating database") }
            .subscribeOn(Schedulers.io())
            .subscribe(
                { updateCachedUsers(it) },
                { handleErrors(it) }
            )
            .addTo(compositeDisposable) // Extension function
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getUsersFromApiAsSingle(): Single<List<DetailedUser>> {
        return rxGetUsersFromApi(NoParameters())
            .take(USER_LIMIT) // Github API has a hourly call limit :D and 5 are enough for what we're doing
            .flatMapMaybe { rxGetUserDetailsFromApi(it.username) } // second api call with information from the first one
            .toList() // gather all stream events back into one list -> List<DetailedUser>
    }

    private fun rxDeleteUser(id: Id) {
        rxDeleteCachedUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { Logger.d("Imagine this is a toast. User deleted successfully.") }
            .addTo(compositeDisposable)
    }

    /******************************** Coroutines ********************************/
    @ExperimentalCoroutinesApi
    private fun setUserViewStateFromCacheWithCoroutines() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            handleErrors(throwable)
        }

        viewModelScope.launch(exceptionHandler) {
            getCachedUsers(NoParameters())
                .mapListElements { displayedDetailedUserMapper.mapToUI(it) }
                .flowOn(backgroundDispatcher)
                .collect { handleDetailedUsers(it) }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun updateCacheWithCoroutines() {
        // I don't like try-catch. So we're using an exception handler instead
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            handleErrors(throwable)
        }

        // we want the coroutine to be bounded to the ViewModel's lifecycle
        viewModelScope.launch(exceptionHandler) {
            // But the request should go to the background. However, Retrofit has its custom dispatcher, so we're good
            val users = getUsersFromApiThroughCoroutine(coroutineScope = this)

            if (users.isNotEmpty()) {
                Logger.d("Updating database")
                // as for Room, we have to be excplicit on the background. Since it doesn't have its own coroutine
                // dispatcher, it'll complain about database access in main thread.
                withContext(backgroundDispatcher) { updateCachedUsers(users) }
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    suspend fun getUsersFromApiThroughCoroutine(coroutineScope: CoroutineScope): List<DetailedUser> {
        return getUsersFromApi(NoParameters()) // List<User>
            .take(USER_LIMIT.toInt()) // Github API has a hourly call limit :D and 5 are enough for what we're doing
            .map { coroutineScope.async { getUserDetailsFromApi(it.username) } } // Yay concurrency!
            .map { it.await() } // Wait for them to finish... These two last maps are pretty much a flatMap
    }

    private fun handleErrors(error: Throwable?) {
        // TODO deal with the exceptions
        Logger.e(error, "Error")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}