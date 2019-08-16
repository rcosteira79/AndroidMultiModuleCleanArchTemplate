package com.rcosteira.rxjavatocoroutines.presentation

interface CardButtonClickListener<in T> {
    fun onButtonClicked(item: T)
}
