package com.rcosteira.core.data.api

import com.rcosteira.core.data.entities.GithubUser
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {
    @GET("users")
    fun getAllUsersAsync(): Deferred<List<GithubUser>>
}