package com.rcosteira.core.di.modules

import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.api.ApiImpl
import com.rcosteira.core.data.preferences.Preferences
import com.rcosteira.core.data.preferences.PreferencesImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideApi(apiImpl: ApiImpl): Api = apiImpl


    @Singleton
    @Provides
    fun providePreferences(preferencesImpl: PreferencesImpl): Preferences = preferencesImpl
}