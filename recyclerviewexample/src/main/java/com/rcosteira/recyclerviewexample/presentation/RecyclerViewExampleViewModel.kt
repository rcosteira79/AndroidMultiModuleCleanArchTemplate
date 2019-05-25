package com.rcosteira.recyclerviewexample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.interactors.UseCase
import com.rcosteira.core.ui.BaseViewModel
import com.rcosteira.recyclerviewexample.domain.entities.User
import com.rcosteira.recyclerviewexample.domain.usecases.GetUsers
import com.rcosteira.recyclerviewexample.presentation.RecyclerViewExampleViewState.GotUsers
import com.rcosteira.recyclerviewexample.presentation.RecyclerViewExampleViewState.SelectedUsersChanged
import com.rcosteira.recyclerviewexample.presentation.entities.DisplayedUser
import com.rcosteira.recyclerviewexample.presentation.mappers.DisplayedUserMapper
import javax.inject.Inject

class RecyclerViewExampleViewModel @Inject constructor(
    private val getUsers: GetUsers,
    private val displayedUserMapper: DisplayedUserMapper
) : BaseViewModel() {

    val viewState: LiveData<RecyclerViewExampleViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<RecyclerViewExampleViewState> = MutableLiveData()

    private var numberOfSelectedUsers: Int = 0
    private var userList: List<DisplayedUser> = emptyList()

    init {
        _viewState.value = RecyclerViewExampleViewState.Loading
        getUsers()
        updateButtonLabel()
    }

    fun updateButtonLabel() {
        val label = if (numberOfSelectedUsers > 0) " ($numberOfSelectedUsers)" else ""
        _viewState.value = SelectedUsersChanged(label)
    }

    private fun getUsers() {
        if (userList.isNotEmpty()) {
            _viewState.value = GotUsers(userList)
        } else {
            getUsers(uiScope, UseCase.None()) {
                it.either(
                    ::handleFailure,
                    ::handleUserList
                )
            }
        }
    }

    private fun handleFailure(failure: Failure) {
        _viewState.value = RecyclerViewExampleViewState.PossibleFailure(failure)
    }


    private fun handleUserList(users: List<User>) {
        val usersToDisplay = users.map { displayedUserMapper.mapToUI(it) }
        userList = usersToDisplay
        _viewState.value = GotUsers(usersToDisplay)
    }

    fun updateNumberOfSelectedUsers(checked: Boolean) {
        when (checked) {
            true -> numberOfSelectedUsers++
            false -> numberOfSelectedUsers--
        }
    }
}