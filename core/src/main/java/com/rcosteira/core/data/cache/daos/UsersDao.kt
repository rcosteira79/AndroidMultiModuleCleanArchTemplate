package com.rcosteira.core.data.cache.daos

import androidx.room.Dao
import androidx.room.Query
import com.rcosteira.core.data.entities.GithubDetailedUser

@Dao
abstract class UsersDao : BaseDao<GithubDetailedUser> {

    @Query("SELECT * from Users")
    abstract fun getAllUsers(): List<GithubDetailedUser>
}