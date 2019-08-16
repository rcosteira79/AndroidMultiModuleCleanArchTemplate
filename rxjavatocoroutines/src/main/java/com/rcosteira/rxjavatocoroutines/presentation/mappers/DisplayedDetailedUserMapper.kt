package com.rcosteira.rxjavatocoroutines.presentation.mappers

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.ui.Mapper
import com.rcosteira.rxjavatocoroutines.presentation.entities.DisplayedDetailedUser
import javax.inject.Inject

class DisplayedDetailedUserMapper @Inject constructor() : Mapper<DetailedUser, DisplayedDetailedUser> {
    override fun mapToUI(type: DetailedUser): DisplayedDetailedUser {
        return DisplayedDetailedUser(
            id = type.id,
            username = type.username,
            name = type.name,
            blog = type.blog,
            location = type.location,
            avatar = type.avatar
        )
    }

}
