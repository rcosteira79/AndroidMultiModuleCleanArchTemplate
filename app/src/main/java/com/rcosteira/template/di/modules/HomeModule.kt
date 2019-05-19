package com.rcosteira.template.di.modules

import com.example.tabswithlists.di.TabsWithListsModule
import com.rcosteira.template.presentation.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @ContributesAndroidInjector(modules = [TabsWithListsModule::class])
    abstract fun provideHomeActivity(): HomeActivity
}