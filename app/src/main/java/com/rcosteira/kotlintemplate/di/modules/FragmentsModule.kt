package com.rcosteira.kotlintemplate.di.modules

import com.rcosteira.kotlintemplate.ui.main.menu.MenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun provideMenuFragment(): MenuFragment
}