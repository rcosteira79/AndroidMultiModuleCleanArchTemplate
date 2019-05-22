package com.rcosteira.recyclerviewexample.di

import androidx.lifecycle.ViewModel
import com.rcosteira.core.di.ViewModelKey
import com.rcosteira.recyclerviewexample.presentation.RecyclerViewExampleFragment
import com.rcosteira.recyclerviewexample.presentation.RecyclerViewExampleViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class RecyclerViewExampleModule {

    @RecyclerViewExampleScope
    @ContributesAndroidInjector
    abstract fun provideRecyclerViewExampleFragment(): RecyclerViewExampleFragment


    @Binds
    @IntoMap
    @ViewModelKey(RecyclerViewExampleViewModel::class)
    abstract fun bindRecyclerViewExampleViewModel(recyclerViewExampleViewModel: RecyclerViewExampleViewModel): ViewModel
}