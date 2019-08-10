package com.rcosteira.core.data.mappers

import com.rcosteira.core.data.Mapper
import com.rcosteira.core.data.entities.GithubDetailedUser
import com.rcosteira.core.domain.*
import com.rcosteira.core.domain.entities.DetailedUser
import java.net.URL
import javax.inject.Inject

class DetailedUserMapper @Inject constructor() : Mapper<DetailedUser, GithubDetailedUser> {
    override fun mapToEntity(type: GithubDetailedUser): DetailedUser {
        return DetailedUser(
            id = Id(type.id),
            username = Username(type.login),
            name = Name(type.name),
            email = Email(type.email),
            location = Location(type.location),
            avatarUrl = URL(type.avatarUrl)
        )
    }

    override fun mapFromEntity(type: DetailedUser): GithubDetailedUser {
        return GithubDetailedUser(
            id = type.id.value,
            login = type.username.value,
            name = type.name.value,
            email = type.email.value,
            location = type.location.value,
            avatarUrl = type.avatarUrl.readText()
        )
    }
}
