package com.rcosteira.core.data.cache

import com.rcosteira.core.data.cache.daos.UsersDao
import com.rcosteira.core.data.entities.GithubDetailedUser
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val usersDao: UsersDao
) : Cache {

    override fun getAllUsers(): Flow<List<GithubDetailedUser>> = usersDao.getAllUsers()

    override fun rxGetAllUsers(): Flowable<List<GithubDetailedUser>> = usersDao.rxGetAllUsers()

    override fun updateCachedUsers(users: List<GithubDetailedUser>) = usersDao.insertAll(*users.toTypedArray())
}