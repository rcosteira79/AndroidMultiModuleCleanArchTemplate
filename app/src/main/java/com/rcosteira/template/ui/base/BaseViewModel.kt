package com.rcosteira.template.ui.base

import androidx.lifecycle.ViewModel
import com.rcosteira.template.data.DataManager
import javax.inject.Inject

open class BaseViewModel @Inject constructor(
        val dataManager: DataManager
): ViewModel()