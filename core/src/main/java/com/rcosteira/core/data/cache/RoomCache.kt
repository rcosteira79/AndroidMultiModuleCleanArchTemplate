package com.rcosteira.core.data.cache

import com.rcosteira.core.data.cache.daos.UsersDao
import com.rcosteira.core.data.entities.GithubDetailedUser
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val usersDao: UsersDao
) : Cache {

    override suspend fun getAllUsers(): List<GithubDetailedUser> = usersDao.getAllUsers()

    override fun rxGetAllUsers(): Flowable<List<GithubDetailedUser>> = usersDao.rxGetAllUsers()

    override fun updateCachedUsers(users: List<GithubDetailedUser>) = usersDao.insertAll(*users.toTypedArray())
}