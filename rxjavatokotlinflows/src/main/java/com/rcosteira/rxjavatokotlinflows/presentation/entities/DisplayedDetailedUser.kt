package com.rcosteira.rxjavatokotlinflows.presentation.entities

import com.rcosteira.core.domain.*

data class DisplayedDetailedUser(
    val id: Id = Id(-1),
    val username: Username = Username(""),
    val name: Name = Name(""),
    val email: Email = Email(""),
    val location: Location = Location(""),
    val avatar: Avatar = Avatar("")
)
