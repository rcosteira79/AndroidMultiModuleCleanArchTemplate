package com.rcosteira.core.data.cache.daos

import androidx.room.*
import com.rcosteira.core.data.entities.GithubDetailedUser
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UsersDao {

    @Query("SELECT * from Users")
    suspend fun getAllUsers(): List<GithubDetailedUser>

    @Query("SELECT * from Users")
    fun rxGetAllUsers(): Flowable<List<GithubDetailedUser>>

    /*
    * These should be generic and go to a BaseDao interface in the case where we need more Daos.
    * UsersDao would then be refactored to an abstract class and would implement said interface.
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: GithubDetailedUser)

    @Delete
    fun delete(user: GithubDetailedUser)
}