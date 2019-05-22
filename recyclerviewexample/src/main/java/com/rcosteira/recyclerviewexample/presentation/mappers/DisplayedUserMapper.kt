package com.rcosteira.recyclerviewexample.presentation.mappers

import com.rcosteira.core.ui.Mapper
import com.rcosteira.recyclerviewexample.domain.entities.User
import com.rcosteira.recyclerviewexample.presentation.entities.DisplayedUser

class DisplayedUserMapper : Mapper<User, DisplayedUser> {
    override fun mapToUI(type: User): DisplayedUser {
        return DisplayedUser(
            id = type.id,
            name = type.name
        )
    }
}