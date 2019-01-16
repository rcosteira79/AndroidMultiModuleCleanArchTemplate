package com.rcosteira.androidkotlintemplate.ui.main.menu

import com.rcosteira.androidkotlintemplate.data.DataManager
import com.rcosteira.androidkotlintemplate.ui.base.BaseViewModel
import javax.inject.Inject

open class MenuViewModel @Inject constructor(
        dataManager: DataManager
) : BaseViewModel(dataManager)