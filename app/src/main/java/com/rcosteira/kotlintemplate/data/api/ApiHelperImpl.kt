package com.rcosteira.kotlintemplate.data.api

import retrofit2.Retrofit
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(retrofit: Retrofit): ApiHelper {

    private val api = retrofit.create(ApiHelper::class.java)
}