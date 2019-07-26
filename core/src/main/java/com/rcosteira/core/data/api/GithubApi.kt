package com.rcosteira.core.data.api

import com.rcosteira.core.data.entities.GithubUser
import retrofit2.Retrofit
import javax.inject.Inject

class GithubApi @Inject constructor(retrofit: Retrofit) : Api {
    private val api = retrofit.create(Api::class.java)

    override suspend fun getAllUsers(): List<GithubUser> {
        return api.getAllUsers()
    }
}
