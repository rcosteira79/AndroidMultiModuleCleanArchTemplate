package com.rcosteira.core.data.api

import com.rcosteira.core.data.entities.GithubUser
import retrofit2.http.GET

interface Api {
    @GET("users")
    suspend fun getAllUsers(): List<GithubUser>
}