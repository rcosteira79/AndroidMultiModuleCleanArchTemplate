package com.rcosteira.core.di.modules

import com.rcosteira.core.data.GithubUsersRepository
import com.rcosteira.core.di.scopes.ActivityScope
import com.rcosteira.core.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ApiModule::class,
        CacheModule::class
    ]
)
abstract class DataModule {

    @Binds
    @ActivityScope
    abstract fun bindRepository(repository: GithubUsersRepository): UsersRepository
}