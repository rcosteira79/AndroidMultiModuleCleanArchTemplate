package com.rcosteira.core.di.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.api.GithubApi
import com.rcosteira.core.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule {


    @Provides
    @ActivityScope
    fun provideApi(githubApi: GithubApi): Api = githubApi


    @Provides
    @ActivityScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}