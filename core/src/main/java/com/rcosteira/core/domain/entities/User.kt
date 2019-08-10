package com.rcosteira.core.domain.entities

import com.rcosteira.core.domain.Id
import com.rcosteira.core.domain.Username

data class User(
    val id: Id,
    val username: Username
)
