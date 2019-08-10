package com.rcosteira.core.domain.entities

import com.rcosteira.core.domain.*
import java.net.URL

data class DetailedUser(
    val id: Id,
    val username: Username,
    val name: Name,
    val email: Email,
    val location: Location,
    val avatarUrl: URL
)
