package com.rcosteira.core.data.mappers

import com.rcosteira.core.data.Mapper
import com.rcosteira.core.data.entities.GithubDetailedUser
import com.rcosteira.core.domain.*
import com.rcosteira.core.domain.entities.DetailedUser
import javax.inject.Inject

class DetailedUserMapper @Inject constructor() : Mapper<DetailedUser, GithubDetailedUser> {
    private val notApplicable = "N/A"

    override fun mapToEntity(type: GithubDetailedUser): DetailedUser {
        return DetailedUser(
            id = Id(type.id),
            username = Username(type.login),
            name = Name(type.name),
            blog = Blog(type.blog ?: notApplicable),
            location = Location(type.location ?: notApplicable),
            avatar = Avatar(type.avatarUrl)
        )
    }

    override fun mapFromEntity(type: DetailedUser): GithubDetailedUser {
        return GithubDetailedUser(
            id = type.id.value,
            login = type.username.value,
            name = type.name.value,
            blog = type.blog.value,
            location = type.location.value,
            avatarUrl = type.avatar.value
        )
    }
}
