package com.rcosteira.core.di.modules

import com.rcosteira.core.data.preferences.Preferences
import com.rcosteira.core.data.preferences.PreferencesImpl
import com.rcosteira.core.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {


    @Provides
    @ActivityScope
    fun providePreferences(preferencesImpl: PreferencesImpl): Preferences = preferencesImpl
}