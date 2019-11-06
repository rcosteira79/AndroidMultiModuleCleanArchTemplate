package com.rcosteira.rxjavatocoroutines.di

import androidx.lifecycle.ViewModel
import com.rcosteira.core.di.ViewModelKey
import com.rcosteira.rxjavatocoroutines.presentation.RxJavaToCoroutinesFragment
import com.rcosteira.rxjavatocoroutines.presentation.RxJavaToCoroutinesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
abstract class RxJavaToCoroutinesModule {

    @RxJavaToCoroutinesScope
    @ContributesAndroidInjector
    abstract fun provideRxJavaToCoroutinesFragment(): RxJavaToCoroutinesFragment

    @Binds
    @IntoMap
    @ViewModelKey(RxJavaToCoroutinesViewModel::class)
    abstract fun bindRxJavaToCoroutinesViewModel(rxJavaToCoroutinesViewModel: RxJavaToCoroutinesViewModel): ViewModel

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

        @JvmStatic
        @Provides
        fun provideBackgroundDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }
}