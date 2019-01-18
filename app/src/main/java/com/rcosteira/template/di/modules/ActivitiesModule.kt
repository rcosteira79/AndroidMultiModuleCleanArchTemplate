package com.rcosteira.template.di.modules

import com.rcosteira.template.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector (modules = [FragmentsModule::class])
    abstract fun provideMainActivity(): MainActivity
}