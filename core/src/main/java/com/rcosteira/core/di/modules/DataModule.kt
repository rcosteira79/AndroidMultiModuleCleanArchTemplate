package com.rcosteira.core.di.modules

import com.rcosteira.core.di.modules.ApiModule
import com.rcosteira.core.di.scopes.ActivityScope
import com.rcosteira.core.domain.repositories.Repository
import com.rcosteira.core.data.GithubRepository
import dagger.Binds
import dagger.Module

@Module(includes = [ApiModule::class])
abstract class DataModule {

    @Binds
    @ActivityScope
    abstract fun bindRepository(repository: GithubRepository): Repository
}