package com.rcosteira.androidkotlintemplate.di.modules

import com.rcosteira.androidkotlintemplate.data.DataManager
import com.rcosteira.androidkotlintemplate.data.DataManagerImpl
import com.rcosteira.androidkotlintemplate.data.api.ApiHelper
import com.rcosteira.androidkotlintemplate.data.api.ApiHelperImpl
import com.rcosteira.androidkotlintemplate.data.database.DatabaseHelper
import com.rcosteira.androidkotlintemplate.data.database.DatabaseHelperImpl
import com.rcosteira.androidkotlintemplate.data.preferences.PreferencesHelper
import com.rcosteira.androidkotlintemplate.data.preferences.PreferencesHelperImpl
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