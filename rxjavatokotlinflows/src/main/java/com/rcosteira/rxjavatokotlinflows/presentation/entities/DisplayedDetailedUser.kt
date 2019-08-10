package com.rcosteira.rxjavatokotlinflows.presentation.entities

import com.rcosteira.core.domain.*
import java.net.URL

data class DisplayedDetailedUser(
    val id: Id,
    val username: Username,
    val name: Name,
    val email: Email,
    val location: Location,
    val picture: URL
)
