package com.rcosteira.recyclerviewexample.presentation

sealed class RecyclerViewExampleEvents {
    object GetUsersEvent : RecyclerViewExampleEvents()
    data class ItemWasCheckboxedEvent(val itemIsChecked: Boolean, val position: Int) : RecyclerViewExampleEvents()
}