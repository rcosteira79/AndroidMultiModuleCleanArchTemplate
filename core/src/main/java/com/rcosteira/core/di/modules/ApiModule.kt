package com.rcosteira.core.di.modules

import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.api.GithubApi
import com.rcosteira.core.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}