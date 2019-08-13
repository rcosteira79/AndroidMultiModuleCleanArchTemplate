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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RxJavaToKotlinFlowsViewModel @Inject constructor(
    private val getUsers: GetUsers,
    private val getUserDetails: GetUserDetails,
    private val getCachedUsers: GetCachedUsers,
    private val rxGetUsersFromApi: RxGetUsersFromApi,
    private val updateCachedUsers: UpdateCachedUsers,
    private val rxGetUserDetails: RxGetUserDetails,
    private val rxGetCachedUsers: RxGetCachedUsers,
    private val displayedDetailedUserMapper: DisplayedDetailedUserMapper,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel() {

    val viewState: LiveData<RxJavaToKotlinFlowsViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<RxJavaToKotlinFlowsViewState> = MutableLiveData()

    init {
        _viewState.value = RxJavaToKotlinFlowsViewState(isLoading = true)
        getUsersWithRx()
        updateCacheWithRx()
    }

    private fun getUsersWithRx() {
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

    private fun updateCacheWithRx() {
        rxGetUsersFromApi(NoParameters())
            .take(5) // Github API has left hourly call limit :D and 5 are enough for what we're doing
            .flatMapMaybe { rxGetUserDetails(it.username) } // second api call with information from the first one
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
        // might not even have the Failure classes if this was RxJava only, so I'm not even gonna bother :D
        Logger.e(error, "Error")
    }

    /******************************** Coroutines ********************************/
    private fun getUsersWithCoroutines() {

        // we want the coroutine to be bounded to the ViewModel's lifecycle in order to publish the changes to the UI
        viewModelScope.launch {

            // But the request should go to the backgound
            val usersOrFailure = withContext(Dispatchers.IO) {

                // TODO on the repo, this should be a Flow in order to replace FLowable!
                // getCachedUsers
            }


        }
    }


    private fun handleFailure(failure: Failure) {
        _viewState.value = RxJavaToKotlinFlowsViewState(isLoading = false, possibleFailure = failure)
    }
}



