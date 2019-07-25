package com.rcosteira.recyclerviewexample.di

import com.rcosteira.core.di.modules.ApiModule
import com.rcosteira.core.di.scopes.ActivityScope
import com.rcosteira.core.domain.repositories.Repository
import com.rcosteira.recyclerviewexample.data.GithubRepository
import dagger.Binds
import dagger.Module

@Module(includes = [ApiModule::class])
abstract class DataModule {

    @Binds
    @ActivityScope
    abstract fun bindRepository(repository: GithubRepository): Repository
}