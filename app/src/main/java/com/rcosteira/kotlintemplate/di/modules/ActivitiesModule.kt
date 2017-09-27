package com.rcosteira.kotlintemplate.di.modules

import com.rcosteira.kotlintemplate.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector (modules = arrayOf(FragmentsModule::class))
    abstract fun provideMainActivity(): MainActivity
}