package com.rcosteira.kotlintemplate.di.modules

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TestNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return mock() // TODO: Replace with real implementation, but mockWebServer
    }
}