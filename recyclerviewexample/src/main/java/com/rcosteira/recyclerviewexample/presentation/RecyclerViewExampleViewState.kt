package com.rcosteira.recyclerviewexample.presentation

import com.rcosteira.core.exception.Failure
import com.rcosteira.recyclerviewexample.presentation.entities.DisplayedUser

sealed class RecyclerViewExampleViewState {
    object Loading : RecyclerViewExampleViewState()
    data class GotUsers(val users: List<DisplayedUser>) : RecyclerViewExampleViewState()
    data class SelectedUsersChanged(val selectedUsers: String) : RecyclerViewExampleViewState()
    data class PossibleFailure(val failure: Failure) : RecyclerViewExampleViewState()
}