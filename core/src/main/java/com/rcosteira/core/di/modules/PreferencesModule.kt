package com.rcosteira.core.di.modules

import com.rcosteira.core.data.preferences.Preferences
import com.rcosteira.core.data.preferences.PreferencesImpl
import com.rcosteira.core.di.scopes.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class PreferencesModule {

    @Binds
    @ActivityScope
    abstract fun providePreferences(preferencesImpl: PreferencesImpl): Preferences
}