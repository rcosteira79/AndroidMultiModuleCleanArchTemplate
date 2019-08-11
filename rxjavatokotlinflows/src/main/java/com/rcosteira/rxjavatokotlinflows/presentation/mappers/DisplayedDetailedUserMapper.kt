package com.rcosteira.rxjavatokotlinflows.presentation.mappers

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.ui.Mapper
import com.rcosteira.rxjavatokotlinflows.presentation.entities.DisplayedDetailedUser
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
