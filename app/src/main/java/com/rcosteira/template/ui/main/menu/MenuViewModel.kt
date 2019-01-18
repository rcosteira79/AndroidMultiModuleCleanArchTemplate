package com.rcosteira.template.ui.main.menu

import com.rcosteira.template.data.DataManager
import com.rcosteira.template.ui.base.BaseViewModel
import javax.inject.Inject

open class MenuViewModel @Inject constructor(
        dataManager: DataManager
) : BaseViewModel(dataManager)