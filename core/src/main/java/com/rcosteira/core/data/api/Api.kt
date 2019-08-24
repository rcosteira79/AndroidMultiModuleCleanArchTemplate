package com.rcosteira.core.data.api

import com.rcosteira.core.data.entities.GithubDetailedUser
import com.rcosteira.core.data.entities.GithubUser
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    /****************************** Coroutine calls ******************************/
    @GET(ApiPaths.users)
    suspend fun getAllUsers(): List<GithubUser>

    @GET("${ApiPaths.users}/{username}")
    suspend fun getUserDetails(@Path("username") username: String): GithubDetailedUser

    /****************************** RxJava calls ******************************/
    @GET(ApiPaths.users)
    fun rxGetAllUsers(): Maybe<List<GithubUser>>

    @GET("${ApiPaths.users}/{username}")
    fun rxGetUserDetails(@Path("username") username: String): Maybe<GithubDetailedUser>
}