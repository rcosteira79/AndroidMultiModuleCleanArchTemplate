package com.rcosteira.recyclerviewexample.presentation

import com.rcosteira.core.exception.Failure
import com.rcosteira.core.exception.Failure.NoFailure
import com.rcosteira.recyclerviewexample.presentation.entities.DisplayedUser

data class RecyclerViewExampleViewState(
    val loading: Boolean = true,
    val userList: List<DisplayedUser> = emptyList(),
    val buttonLabel: String = "",
    val selectedUsers: Int = 0,
    val possibleFailure: Failure = NoFailure
)
