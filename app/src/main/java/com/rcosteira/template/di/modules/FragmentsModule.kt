package com.rcosteira.template.di.modules

import com.rcosteira.template.ui.main.menu.MenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun provideMenuFragment(): MenuFragment
}