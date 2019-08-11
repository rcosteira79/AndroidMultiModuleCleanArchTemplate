package com.rcosteira.core.di.modules

import android.content.Context
import androidx.room.Room
import com.rcosteira.core.data.cache.Cache
import com.rcosteira.core.data.cache.GithubDatabase
import com.rcosteira.core.data.cache.RoomCache
import com.rcosteira.core.di.scopes.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Binds
    @ActivityScope
    abstract fun bindCache(cache: RoomCache): Cache

    @Module
    companion object {

        @Provides
        @JvmStatic
        @ActivityScope
        fun provideDatabase(context: Context): GithubDatabase {
            return Room.databaseBuilder(context, GithubDatabase::class.java, "users.db")
                .build()
        }

        @Provides
        @JvmStatic
        @ActivityScope
        fun provideUsersDao(githubDatabase: GithubDatabase) = githubDatabase.usersDao()
    }

}