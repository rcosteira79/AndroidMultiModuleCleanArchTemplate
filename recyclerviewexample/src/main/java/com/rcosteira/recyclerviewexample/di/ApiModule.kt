package com.rcosteira.recyclerviewexample.di

import com.rcosteira.recyclerviewexample.data.api.Api
import com.rcosteira.recyclerviewexample.data.api.GithubApi
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    @RecyclerViewExampleScope
    fun provideApi(githubApi: GithubApi): Api = githubApi
}