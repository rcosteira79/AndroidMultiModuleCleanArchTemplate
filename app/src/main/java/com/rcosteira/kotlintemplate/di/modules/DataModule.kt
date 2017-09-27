package com.rcosteira.kotlintemplate.di.modules

import com.rcosteira.kotlintemplate.data.DataManager
import com.rcosteira.kotlintemplate.data.DataManagerImpl
import com.rcosteira.kotlintemplate.data.api.ApiHelper
import com.rcosteira.kotlintemplate.data.api.ApiHelperImpl
import com.rcosteira.kotlintemplate.data.database.DatabaseHelper
import com.rcosteira.kotlintemplate.data.database.DatabaseHelperImpl
import com.rcosteira.kotlintemplate.data.preferences.PreferencesHelper
import com.rcosteira.kotlintemplate.data.preferences.PreferencesHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
class DataModule {

    @Singleton
    @Provides
    fun provideDataManager(dataManagerImpl: DataManagerImpl): DataManager = dataManagerImpl

    @Singleton
    @Provides
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl

    @Singleton
    @Provides
    fun provideDatabaseHelper(databaseHelperImpl: DatabaseHelperImpl): DatabaseHelper = databaseHelperImpl

    @Singleton
    @Provides
    fun providePreferencesHelper(preferencesHelperImpl: PreferencesHelperImpl): PreferencesHelper = preferencesHelperImpl
}