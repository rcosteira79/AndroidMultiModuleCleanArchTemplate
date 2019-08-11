package com.rcosteira.core.data.cache

import com.rcosteira.core.data.cache.daos.UsersDao
import com.rcosteira.core.data.entities.GithubDetailedUser
import com.rcosteira.core.data.entities.GithubUser
import io.reactivex.Single
import javax.inject.Inject

class RoomCache @Inject constructor(
    usersDao: UsersDao
) : Cache {
    override suspend fun getAllUsers(): List<GithubUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUserDetails(username: String): GithubDetailedUser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rxGetAllUsers(): Single<List<GithubUser>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rxGetUserDetails(username: String): Single<GithubDetailedUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}