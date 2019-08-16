package com.rcosteira.core.data.cache

import com.rcosteira.core.data.entities.GithubDetailedUser
import com.rcosteira.core.domain.Id
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface Cache {

    /****************************** Coroutine calls ******************************/
    fun getAllUsers(): Flow<List<GithubDetailedUser>>

    /****************************** RxJava calls ******************************/
    fun rxGetAllUsers(): Flowable<List<GithubDetailedUser>>

    fun rxDeleteCachedUser(id: Id): Completable

    /****************************** Common calls ******************************/
    // This one could have different implementations too but meh, really no point
    fun updateCachedUsers(users: List<GithubDetailedUser>)

}