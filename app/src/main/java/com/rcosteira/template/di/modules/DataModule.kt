package com.rcosteira.template.di.modules

import com.rcosteira.template.data.DataManager
import com.rcosteira.template.data.DataManagerImpl
import com.rcosteira.template.data.api.ApiHelper
import com.rcosteira.template.data.api.ApiHelperImpl
import com.rcosteira.template.data.database.DatabaseHelper
import com.rcosteira.template.data.database.DatabaseHelperImpl
import com.rcosteira.template.data.preferences.PreferencesHelper
import com.rcosteira.template.data.preferences.PreferencesHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
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