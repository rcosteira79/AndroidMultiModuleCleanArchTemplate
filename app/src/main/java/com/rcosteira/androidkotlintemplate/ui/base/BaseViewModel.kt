package com.rcosteira.androidkotlintemplate.ui.base

import androidx.lifecycle.ViewModel
import com.rcosteira.androidkotlintemplate.data.DataManager
import javax.inject.Inject

open class BaseViewModel @Inject constructor(
        val dataManager: DataManager
): ViewModel()