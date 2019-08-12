package com.rcosteira.rxjavatokotlinflows.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rcosteira.core.domain.usecases.GetUsers
import com.rcosteira.core.interactors.UseCase.None
import com.rcosteira.core.ui.BaseViewModel
import com.rcosteira.logging.Logger
import com.rcosteira.rxjavatokotlinflows.domain.usecases.*
import com.rcosteira.rxjavatokotlinflows.presentation.entities.DisplayedDetailedUser
import com.rcosteira.rxjavatokotlinflows.presentation.mappers.DisplayedDetailedUserMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RxJavaToKotlinFlowsViewModel @Inject constructor(
    private val getUsers: GetUsers,
    private val getUserDetails: GetUserDetails,
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
        updateCache()
    }

    private fun getUsersWithRx() {
        rxGetCachedUsers(params = None())
            .doOnNext { Logger.d("Database was updated. Will emit UI update.") }
            .map { detailedUsers ->
                detailedUsers.map { displayedDetailedUserMapper.mapToUI(it) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { this.handleDetailedUsers(it) },
                { this.handleFailure(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun handleDetailedUsers(detailedUsers: List<DisplayedDetailedUser>) {
        _viewState.value = RxJavaToKotlinFlowsViewState(isLoading = false, detailedUsers = detailedUsers)
    }

    private fun updateCache() {
        rxGetUsersFromApi(params = None())
            .take(5) // Github API has a hourly call limit :D and 5 are enough for what we're doing
            .flatMapMaybe { rxGetUserDetails(it.username) } // second api call with information from the first one
            .toList() // gather all stream events back into one list -> List<DisplayedDetailedUser>
            .doOnSuccess { Logger.d("Updating database") }
            .subscribeOn(Schedulers.io())
            .subscribe(
                { updateCachedUsers(it) },
                { this.handleFailure(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun handleFailure(error: Throwable?) {
        // TODO actually account for errors :D
        Logger.e(error, "Error")
    }
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}