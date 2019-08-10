package com.rcosteira.core.data.mappers

import com.rcosteira.core.data.Mapper
import com.rcosteira.core.data.entities.GithubUser
import com.rcosteira.core.domain.Id
import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.entities.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<User, GithubUser> {
    override fun mapToEntity(type: GithubUser): User {
        return User(
            id = Id(type.id),
            username = Username(type.login)
        )
    }

    override fun mapFromEntity(type: User): GithubUser {
        return GithubUser(
            login = type.username.value,
            id = type.id.value
        )
    }

}