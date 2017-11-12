package com.rcosteira.kotlintemplate.di.modules

import com.nhaarman.mockito_kotlin.mock
import com.rcosteira.kotlintemplate.data.DataManager
import com.rcosteira.kotlintemplate.data.api.ApiHelper
import com.rcosteira.kotlintemplate.data.database.DatabaseHelper
import com.rcosteira.kotlintemplate.data.preferences.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(TestNetworkModule::class))
class TestDataModule {

    @Singleton
    @Provides
    fun provideDataManager(): DataManager = mock()

    @Singleton
    @Provides
    fun providePreferencesHelper(): PreferencesHelper = mock()

    @Singleton
    @Provides
    fun provideDatabaseHelper(): DatabaseHelper = mock() // TODO: Replace with real implementation, but fake database

    @Singleton
    @Provides
    fun provideApiHelper(): ApiHelper = mock() // TODO: Replace with real implementation, but mockWebServer
}

