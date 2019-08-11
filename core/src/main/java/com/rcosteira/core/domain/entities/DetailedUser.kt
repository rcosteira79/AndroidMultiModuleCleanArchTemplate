package com.rcosteira.core.domain.entities

import com.rcosteira.core.domain.*

data class DetailedUser(
    val id: Id,
    val username: Username,
    val name: Name,
    val email: Email,
    val location: Location,
    val avatar: Avatar
)
