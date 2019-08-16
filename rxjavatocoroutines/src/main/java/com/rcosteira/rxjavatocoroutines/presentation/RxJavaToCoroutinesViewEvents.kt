package com.rcosteira.rxjavatocoroutines.presentation

import com.rcosteira.core.domain.Id
import com.rcosteira.rxjavatocoroutines.presentation.entities.DisplayedDetailedUser

sealed class RxJavaToCoroutinesViewEvents {
    data class DeleteUser(val id: Id) : RxJavaToCoroutinesViewEvents()
}