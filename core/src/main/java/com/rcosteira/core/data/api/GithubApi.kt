package com.rcosteira.core.data.api

import com.rcosteira.core.data.entities.GithubUser
import com.rcosteira.core.data.entities.GithubDetailedUser
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class GithubApi @Inject constructor(retrofit: Retrofit) : Api {

    private val api = retrofit.create(Api::class.java)

    /****************************** Coroutine calls ******************************/
    override suspend fun getAllUsers(): List<GithubUser> {
        return api.getAllUsers()
    }

    override suspend fun getUserDetails(username: String): GithubDetailedUser {
        return api.getUserDetails(username)
    }

    /****************************** RxJava calls ******************************/
    override fun rxGetAllUsers(): Single<List<GithubUser>> {
        return api.rxGetAllUsers()
    }

    override fun rxGetUserDetails(username: String): Single<GithubDetailedUser> {
        return api.rxGetUserDetails(username)
    }
}
