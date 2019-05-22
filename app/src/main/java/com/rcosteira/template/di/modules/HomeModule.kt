package com.rcosteira.template.di.modules

import com.rcosteira.core.di.scopes.ActivityScope
import com.rcosteira.recyclerviewexample.di.RecyclerViewExampleModule
import com.rcosteira.template.presentation.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [RecyclerViewExampleModule::class])
    abstract fun provideHomeActivity(): HomeActivity
}