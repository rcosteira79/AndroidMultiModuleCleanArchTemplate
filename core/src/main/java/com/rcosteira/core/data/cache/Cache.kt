package com.rcosteira.core.data.cache

import com.rcosteira.core.data.entities.GithubDetailedUser
import io.reactivex.Flowable
import io.reactivex.Single

interface Cache {

    /****************************** Coroutine calls ******************************/
    suspend fun getAllUsers(): List<GithubDetailedUser>

    /****************************** RxJava calls ******************************/
    fun rxGetAllUsers(): Flowable<List<GithubDetailedUser>>

    /****************************** Common calls ******************************/
    fun updateCachedUsers(users: List<GithubDetailedUser>)
}