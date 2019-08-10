package com.rcosteira.rxjavatokotlinflows.presentation

import com.rcosteira.core.exception.Failure
import com.rcosteira.rxjavatokotlinflows.presentation.entities.DisplayedDetailedUser

data class RxJavaToKotlinFlowsViewState(
    val isLoading: Boolean = false,
    val detailedUsers: List<DisplayedDetailedUser> = emptyList(),
    val possibleFailure: Failure = Failure.NoFailure
)
