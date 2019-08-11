package com.rcosteira.core.data.cache

import com.rcosteira.core.data.entities.GithubDetailedUser
import com.rcosteira.core.data.entities.GithubUser
import io.reactivex.Single

interface Cache {

    /****************************** Coroutine calls ******************************/
    suspend fun getAllUsers(): List<GithubUser>

    suspend fun getUserDetails(username: String): GithubDetailedUser

    /****************************** RxJava calls ******************************/
    fun rxGetAllUsers(): Single<List<GithubUser>>

    fun rxGetUserDetails(username: String): Single<GithubDetailedUser>
}