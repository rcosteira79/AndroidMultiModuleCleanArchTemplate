package com.rcosteira.core.data.cache

import com.rcosteira.core.data.entities.GithubDetailedUser
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface Cache {

    /****************************** Coroutine calls ******************************/
    fun getAllUsers(): Flow<List<GithubDetailedUser>>

    /****************************** RxJava calls ******************************/
    fun rxGetAllUsers(): Flowable<List<GithubDetailedUser>>

    /****************************** Common calls ******************************/
    fun updateCachedUsers(users: List<GithubDetailedUser>)
}