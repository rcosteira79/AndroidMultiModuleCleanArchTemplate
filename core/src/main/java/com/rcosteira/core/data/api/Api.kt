package com.rcosteira.core.data.api

import com.rcosteira.core.data.entities.GithubUser
import com.rcosteira.core.data.entities.GithubDetailedUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    /****************************** Coroutine calls ******************************/
    @GET("users")
    suspend fun getAllUsers(): List<GithubUser>

    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): GithubDetailedUser

    /****************************** RxJava calls ******************************/
    @GET("users")
    fun rxGetAllUsers(): Single<List<GithubUser>>

    @GET("users/{username}")
    fun rxGetUserDetails(@Path("username") username: String): Single<GithubDetailedUser>
}