package com.rcosteira.recyclerviewexample.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rcosteira.recyclerviewexample.data.api.Api
import com.rcosteira.recyclerviewexample.data.api.GithubApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule {


    @Provides
    @RecyclerViewExampleScope
    fun provideApi(githubApi: GithubApi): Api = githubApi


    @Provides
    @RecyclerViewExampleScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}