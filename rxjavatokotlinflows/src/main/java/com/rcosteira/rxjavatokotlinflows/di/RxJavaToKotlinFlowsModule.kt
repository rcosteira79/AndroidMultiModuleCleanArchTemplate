package com.rcosteira.rxjavatokotlinflows.di

import androidx.lifecycle.ViewModel
import com.rcosteira.core.di.ViewModelKey
import com.rcosteira.rxjavatokotlinflows.presentation.RxJavaToKotlinFlowsFragment
import com.rcosteira.rxjavatokotlinflows.presentation.RxJavaToKotlinFlowsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class RxJavaToKotlinFlowsModule {

    @RxJavaToKotlinFlowsScope
    @ContributesAndroidInjector
    abstract fun provideRxJavaToKotlinFlowsFragment(): RxJavaToKotlinFlowsFragment

    @Binds
    @IntoMap
    @ViewModelKey(RxJavaToKotlinFlowsViewModel::class)
    abstract fun bindRxJavaToKotlinFlowsViewModel(rxJavaToKotlinFlowsViewModel: RxJavaToKotlinFlowsViewModel): ViewModel

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
}