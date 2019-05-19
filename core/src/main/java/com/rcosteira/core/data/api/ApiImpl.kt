package com.rcosteira.core.data.api

import retrofit2.Retrofit
import javax.inject.Inject

class ApiImpl @Inject constructor(retrofit: Retrofit): Api {

    private val api = retrofit.create(Api::class.java)
}