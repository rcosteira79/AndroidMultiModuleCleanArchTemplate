package com.rcosteira.androidkotlintemplate.di.modules

import com.rcosteira.androidkotlintemplate.ui.main.menu.MenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun provideMenuFragment(): MenuFragment
}