package com.rcosteira.rxjavatocoroutines.presentation.entities

import com.rcosteira.core.domain.*

data class DisplayedDetailedUser(
    val id: Id = Id(-1),
    val username: Username = Username(""),
    val name: Name = Name(""),
    val blog: Blog = Blog(""),
    val location: Location = Location(""),
    val avatar: Avatar = Avatar("")
)
