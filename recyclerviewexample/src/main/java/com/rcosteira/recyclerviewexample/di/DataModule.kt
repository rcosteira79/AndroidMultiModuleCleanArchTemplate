package com.rcosteira.recyclerviewexample.di

import com.rcosteira.recyclerviewexample.data.GithubRepository
import com.rcosteira.recyclerviewexample.domain.repositories.Repository
import dagger.Binds
import dagger.Module

@Module(includes = [ApiModule::class])
abstract class DataModule {

    @Binds
    @RecyclerViewExampleScope
    abstract fun bindRepository(repository: GithubRepository): Repository
}