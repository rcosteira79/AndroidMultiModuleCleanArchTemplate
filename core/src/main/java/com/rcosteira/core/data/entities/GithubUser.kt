package com.rcosteira.core.data.entities

import com.squareup.moshi.Json

data class GithubUser(
    @field:Json(name = "login") val login: String = "",
    @field:Json(name = "id") internal val id: Long = -1
)
