package com.rcosteira.recyclerviewexample.data.api

import com.rcosteira.recyclerviewexample.data.entities.GithubUser
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {
    @GET("users")
    fun getAllUsersAsync(): Deferred<List<GithubUser>>
}