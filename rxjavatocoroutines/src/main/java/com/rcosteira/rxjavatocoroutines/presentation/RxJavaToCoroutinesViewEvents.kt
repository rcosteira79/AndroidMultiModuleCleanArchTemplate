package com.rcosteira.rxjavatocoroutines.presentation

import com.rcosteira.core.domain.Id

sealed class RxJavaToCoroutinesViewEvents {
    object RequestUsers : RxJavaToCoroutinesViewEvents()
    data class DeleteUser(val id: Id) : RxJavaToCoroutinesViewEvents()
}