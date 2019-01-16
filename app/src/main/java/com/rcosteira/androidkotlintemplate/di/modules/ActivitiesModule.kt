package com.rcosteira.androidkotlintemplate.di.modules

import com.rcosteira.androidkotlintemplate.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector (modules = [FragmentsModule::class])
    abstract fun provideMainActivity(): MainActivity
}