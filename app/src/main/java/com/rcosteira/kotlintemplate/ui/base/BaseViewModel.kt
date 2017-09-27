package com.rcosteira.kotlintemplate.ui.base

import android.arch.lifecycle.ViewModel
import com.rcosteira.kotlintemplate.data.DataManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor(val dataManager: DataManager, val compositeDisposable: CompositeDisposable): ViewModel() {

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}