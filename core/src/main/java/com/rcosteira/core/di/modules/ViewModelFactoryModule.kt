package com.rcosteira.core.di.modules

import androidx.lifecycle.ViewModelProvider
import com.rcosteira.core.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}