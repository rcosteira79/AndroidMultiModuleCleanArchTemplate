package com.rcosteira.template.di.modules

import com.rcosteira.template.presentation.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @ContributesAndroidInjector
    abstract fun provideHomeActivity(): HomeActivity
}