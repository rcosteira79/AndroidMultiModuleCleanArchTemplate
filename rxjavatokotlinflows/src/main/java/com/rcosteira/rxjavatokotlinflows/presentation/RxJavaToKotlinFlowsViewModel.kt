package com.rcosteira.rxjavatokotlinflows.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rcosteira.core.interactors.UseCase.None
import com.rcosteira.core.ui.BaseViewModel
import com.rcosteira.rxjavatokotlinflows.domain.usecases.RxGetUserDetails
import com.rcosteira.rxjavatokotlinflows.domain.usecases.RxGetUsers
import com.rcosteira.rxjavatokotlinflows.presentation.entities.DisplayedDetailedUser
import com.rcosteira.rxjavatokotlinflows.presentation.mappers.DisplayedDetailedUserMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RxJavaToKotlinFlowsViewModel @Inject constructor(
    private val getUsers: RxGetUsers,
    private val getUserDetails: RxGetUserDetails,
    private val displayedDetailedUserMapper: DisplayedDetailedUserMapper,
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel() {

    val viewState: LiveData<RxJavaToKotlinFlowsViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<RxJavaToKotlinFlowsViewState> = MutableLiveData()

    init {
        _viewState.value = RxJavaToKotlinFlowsViewState(isLoading = true)
    }

    private fun getDetailedUsersWithRx() {
        getUsers(None()) // we use a singles for semantic purposes - we only get one response on each api request.
            .flattenAsObservable { it } // however, in order to turn the list into a stream, we need to turn it into an observable
            .flatMapSingle { getUserDetails(it.username) } // second api call with information from the first one
            .map { displayedDetailedUserMapper.mapToUI(it) }
            .toList() // gather all stream events back into one list -> List<DisplayedDetailedUser>
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { this.handleDetailedUsers(it) },
                { this.handleFailure(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun handleFailure(error: Throwable?) {
        // TODO
    }

    private fun handleDetailedUsers(detailedUsers: List<DisplayedDetailedUser>) {
        _viewState.value = RxJavaToKotlinFlowsViewState(isLoading = false, detailedUsers = detailedUsers)
    }

    private fun getUsersWithDetailsThroughCoroutines() {

    }
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}