package com.rcosteira.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(
    owner: LifecycleOwner,
    liveData: L,
    body: (T) -> Unit
) =
    liveData.observe(owner, Observer(body))