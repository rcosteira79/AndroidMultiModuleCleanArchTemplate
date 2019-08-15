package com.rcosteira.rxjavatokotlinflows.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.extensions.addTo
import com.rcosteira.core.extensions.mapListElements
import com.rcosteira.core.interactors.CoroutineScopeUseCase.NoParameters
import com.rcosteira.core.ui.BaseViewModel
import com.rcosteira.logging.Logger
import com.rcosteira.rxjavatokotlinflows.domain.usecases.*
import com.rcosteira.rxjavatokotlinflows.presentation.entities.DisplayedDetailedUser
import com.rcosteira.rxjavatokotlinflows.presentation.mappers.DisplayedDetailedUserMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RxJavaToKotlinFlowsViewModel @Inject constructor(
    private val getUsersFromApi: GetUsersFromApi,
    private val getUserDetailsFromApi: GetUserDetailsFromApi,
    private val getCachedUsers: GetCachedUsers,
    private val rxGetUsersFromApi: RxGetUsersFromApi,
    private val rxGetUserDetailsFromApi: RxGetUserDetailsFromApi,
    private val rxGetCachedUsers: RxGetCachedUsers,
    private val updateCachedUsers: UpdateCachedUsers,
    private val displayedDetailedUserMapper: DisplayedDetailedUserMapper,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel() {

    companion object {
        const val USER_LIMIT: Long = 5
    }

    val viewState: LiveData<RxJavaToKotlinFlowsViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<RxJavaToKotlinFlowsViewState> = MutableLiveData()

    init {
        _viewState.value = RxJavaToKotlinFlowsViewState(isLoading = true)
        setUserViewStateFromCacheWithRx()
        updateCacheWithRx()
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
                { this.handleRxFailure(it) }
            )
            .addTo(compositeDisposable) // Extension function
    }

    private fun handleDetailedUsers(detailedUsers: List<DisplayedDetailedUser>) {
        _viewState.value = RxJavaToKotlinFlowsViewState(isLoading = false, detailedUsers = detailedUsers)
    }

    // Gets users from the api and stores them in the database
    private fun updateCacheWithRx() {
        rxGetUsersFromApi(NoParameters())
            .take(USER_LIMIT) // Github API has left hourly call limit :D and 5 are enough for what we're doing
            .flatMapMaybe { rxGetUserDetailsFromApi(it.username) } // second api call with information from the first one
            .toList() // gather all stream events back into one list -> List<DisplayedDetailedUser>
            .doOnSuccess { Logger.d("Updating database") }
            .subscribeOn(Schedulers.io())
            .subscribe(
                { updateCachedUsers(it) },
                { this.handleRxFailure(it) }
            )
            .addTo(compositeDisposable) // Extension function
    }

    private fun handleRxFailure(error: Throwable?) {
        // TODO deal with the exceptions
        Logger.e(error, "Error")
    }

    /******************************** Coroutines ********************************/
    private fun setUserViewStateFromCacheWithCoroutines() {
        // TODO implement
    }

    private fun updateCacheWithCoroutines() {
        // we want the coroutine to be bounded to the ViewModel's lifecycle
        viewModelScope.launch {
            // But the request should go to the backgound
            withContext(Dispatchers.IO) {
                val userList = getUsersFromApi(NoParameters()) // List<User>
                    .take(USER_LIMIT.toInt()) // Github API has left hourly call limit :D and 5 are enough for what we're doing
                    .map { async { getUserDetailsFromApi(it.username) } } // Yay concurrency!
                    .map { it.await() } // Wait for them to finish... These two last maps are pretty much a flatMap

                if (userList.isNotEmpty()) {
                    Logger.d("Updating database")
                    updateCachedUsers(userList)
                } else {
                    // TODO handle errors
                }
            }

            // Don't forget: at this point, we're in the main thread context!
        }
    }

    private fun handleFailure(failure: Failure) {
        _viewState.value = RxJavaToKotlinFlowsViewState(isLoading = false, possibleFailure = failure)
    }
}