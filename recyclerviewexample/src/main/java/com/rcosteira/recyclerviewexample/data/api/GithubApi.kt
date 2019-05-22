package com.rcosteira.recyclerviewexample.data.api

import com.rcosteira.recyclerviewexample.data.entities.GithubUser
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class GithubApi @Inject constructor(retrofit: Retrofit) : Api {

    private val api = retrofit.create(Api::class.java)

    override fun getAllUsersAsync(): Deferred<List<GithubUser>> {
        return api.getAllUsersAsync()
    }
}
