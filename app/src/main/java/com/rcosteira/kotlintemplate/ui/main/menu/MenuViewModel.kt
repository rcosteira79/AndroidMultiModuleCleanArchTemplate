package com.rcosteira.kotlintemplate.ui.main.menu

import com.rcosteira.kotlintemplate.data.DataManager
import com.rcosteira.kotlintemplate.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MenuViewModel @Inject constructor(
        dataManager: DataManager,
        compositeDisposable: CompositeDisposable
): BaseViewModel(dataManager, compositeDisposable) {

}