package com.rcosteira.core.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rcosteira.core.data.cache.daos.UsersDao
import com.rcosteira.core.data.entities.GithubDetailedUser

@Database(
    entities = [
        GithubDetailedUser::class
    ], version = 1
)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}