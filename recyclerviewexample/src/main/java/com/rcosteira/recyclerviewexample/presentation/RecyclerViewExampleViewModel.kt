package com.rcosteira.recyclerviewexample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.interactors.UseCase
import com.rcosteira.core.ui.BaseViewModel
import com.rcosteira.recyclerviewexample.domain.entities.User
import com.rcosteira.recyclerviewexample.domain.usecases.GetUsers
import com.rcosteira.recyclerviewexample.presentation.RecyclerViewExampleEvents.GetUsersEvent
import com.rcosteira.recyclerviewexample.presentation.RecyclerViewExampleEvents.ItemWasCheckboxedEvent
import com.rcosteira.recyclerviewexample.presentation.mappers.DisplayedUserMapper
import javax.inject.Inject

class RecyclerViewExampleViewModel @Inject constructor(
    private val getUsers: GetUsers,
    private val displayedUserMapper: DisplayedUserMapper
) : BaseViewModel() {

    val viewState: LiveData<RecyclerViewExampleViewState>
        get() = _viewState

    private val _viewState: MutableLiveData<RecyclerViewExampleViewState> = MutableLiveData()

    init {
        _viewState.value = RecyclerViewExampleViewState()
        getUsers()
    }

    fun processEvents(vararg events: RecyclerViewExampleEvents) {
        events.forEach {
            when (it) {
                is GetUsersEvent -> getUsers()
                is ItemWasCheckboxedEvent -> updateSelectedUsers(it.itemIsChecked, it.position)
            }
        }
    }

    private fun updateSelectedUsers(itemIsChecked: Boolean, position: Int) {
        var checkedItems = _viewState.value?.selectedUsers ?: 0
        when (itemIsChecked) {
            true -> checkedItems += 1
            false -> checkedItems -= 1
        }

        val buttonLabel = getStringForLabel(checkedItems)

        _viewState.value?.userList?.let {
            it[position].isChecked = itemIsChecked
            _viewState.value =
                _viewState.value?.copy(userList = it, buttonLabel = buttonLabel, selectedUsers = checkedItems)
        }
    }

    private fun getStringForLabel(numberOfSelectedUsers: Int): String {
        return if (numberOfSelectedUsers > 0) " ($numberOfSelectedUsers)" else ""
    }

    // TODO check if we already have the user list before calling the API
    private fun getUsers() = getUsers(uiScope, UseCase.None()) {
        it.either(
            ::handleFailure,
            ::handleUserList
        )
    }

    private fun handleFailure(failure: Failure) {
        _viewState.value = _viewState.value?.copy(possibleFailure = failure)
    }

    private fun handleUserList(users: List<User>) {
        val usersToDisplay = users.map { displayedUserMapper.mapToUI(it) }
        _viewState.value = _viewState.value?.copy(userList = usersToDisplay)
    }
}