package com.rcosteira.recyclerviewexample.data.mappers

import com.rcosteira.core.data.Mapper
import com.rcosteira.core.data.entities.GithubUser
import com.rcosteira.core.domain.entities.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<User, GithubUser> {
    override fun mapToEntity(type: GithubUser): User {
        return User(
            id = type.id,
            name = type.login,
            avatarUrl = type.avatarUrl
        )
    }

    override fun mapFromEntity(type: User): GithubUser {
        return GithubUser(
            login = type.name,
            id = type.id,
            avatarUrl = type.avatarUrl
        )
    }

}