package com.rcosteira.rxjavatokotlinflows

import androidx.lifecycle.ViewModel
import com.rcosteira.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class RxJavaToKotlinFlowsModule {

    @RxJavaToKotlinFlowsScope
    @ContributesAndroidInjector
    abstract fun provideRxJavaToKotlinFlowsFragment(): RxJavaToKotlinFlowsFragment

    @Binds
    @IntoMap
    @ViewModelKey(RxJavaToKotlinFlowsViewModel::class)
    abstract fun bindRxJavaToKotlinFlowsViewModel(rxJavaToKotlinFlowsViewModel: RxJavaToKotlinFlowsViewModel): ViewModel
}