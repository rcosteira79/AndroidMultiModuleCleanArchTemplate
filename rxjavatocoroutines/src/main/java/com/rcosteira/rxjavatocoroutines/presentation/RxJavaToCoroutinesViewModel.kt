package com.rcosteira.rxjavatocoroutines.presentation

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
import com.rcosteira.rxjavatocoroutines.presentation.entities.DisplayedDetailedUser
import com.rcosteira.rxjavatocoroutines.presentation.mappers.DisplayedDetailedUserMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
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
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel() {

    companion object {
        const val USER_LIMIT: Long = 10
    }

    val viewState: LiveData<RxJavaToCoroutinesViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<RxJavaToCoroutinesViewState> = MutableLiveData()

    init {
        _viewState.value = RxJavaToCoroutinesViewState(isLoading = true)
        setUserViewStateFromCacheWithRx()
        updateCacheWithRx()
    }

    fun processEvents(event: RxJavaToCoroutinesViewEvents) {
        when (event) {
            is DeleteUser -> rxDeleteUser(event.id)
        }
    }

    /******************************** RxJava ********************************/

    // Gets users from the database to the view
    private fun setUserViewStateFromCacheWithRx() {
        rxGetCachedUsers(NoParameters())
            .doOnNext { Logger.d("Database was updated. Will emit UI update.") }
            .mapListElements { displayedDetailedUserMapper.mapToUI(it) } // Extension function
            .subscribeOn(Schedulers.io())
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
    private fun updateCacheWithRx() {
        getUsersFromApiAsSingle()
            .doOnSuccess { Logger.d("Updating database") }
            .subscribeOn(Schedulers.io())
            .subscribe(
                { updateCachedUsers(it) },
                { handleErrors(it) }
            )
            .addTo(compositeDisposable) // Extension function
    }

    private fun getUsersFromApiAsSingle(): Single<List<DetailedUser>> {
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
    private fun setUserViewStateFromCacheWithCoroutines() {
        // TODO implement
    }

    private fun updateCacheWithCoroutines() {
        // I don't like try-catch. So we're using an exception handler instead
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            handleErrors(throwable)
        }

        // we want the coroutine to be bounded to the ViewModel's lifecycle (it's on the main thread)
        viewModelScope.launch(exceptionHandler) {
            // But the request should go to the backgound
            withContext(Dispatchers.IO) {
                val userList = getUsersFromApi(NoParameters()) // List<User>
                    .take(USER_LIMIT.toInt()) // Github API has a hourly call limit :D and 5 are enough for what we're doing
                    .map { async { getUserDetailsFromApi(it.username) } } // Yay concurrency!
                    .map { it.await() } // Wait for them to finish... These two last maps are pretty much a flatMap

                if (userList.isNotEmpty()) {
                    Logger.d("Updating database")
                    updateCachedUsers(userList)
                }
            }

            // Don't forget: at this point, we're in the main thread context!
        }
    }


    private fun handleErrors(error: Throwable?) {
        // TODO deal with the exceptions
        Logger.e(error, "Error")
    }

}