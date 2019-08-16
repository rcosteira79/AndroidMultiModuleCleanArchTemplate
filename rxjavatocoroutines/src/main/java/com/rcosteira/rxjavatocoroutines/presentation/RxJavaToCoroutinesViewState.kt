package com.rcosteira.rxjavatocoroutines.presentation

import com.rcosteira.core.exception.Failure
import com.rcosteira.rxjavatocoroutines.presentation.entities.DisplayedDetailedUser

data class RxJavaToCoroutinesViewState(
    val isLoading: Boolean = false,
    val detailedUsers: List<DisplayedDetailedUser> = emptyList(),
    val possibleFailure: Failure = Failure.NoFailure
)
