package com.rcosteira.core.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rcosteira.core.di.Injectable
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected inline fun <reified T : BaseViewModel> createViewModel(fragment: Fragment, body: T.() -> Unit): T {
        val viewModel = ViewModelProviders.of(fragment, viewModelFactory).get(T::class.java)
        viewModel.body()
        return viewModel
    }
}