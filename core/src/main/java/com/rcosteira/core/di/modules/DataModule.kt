package com.rcosteira.core.di.modules

import com.rcosteira.core.data.api.ApiHelper
import com.rcosteira.core.data.api.ApiHelperImpl
import com.rcosteira.core.data.preferences.PreferencesHelper
import com.rcosteira.core.data.preferences.PreferencesHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl


    @Singleton
    @Provides
    fun providePreferencesHelper(preferencesHelperImpl: PreferencesHelperImpl): PreferencesHelper =
        preferencesHelperImpl
}