package com.rcosteira.recyclerviewexample.data.mappers

import com.rcosteira.core.data.Mapper
import com.rcosteira.recyclerviewexample.data.entities.GithubUser
import com.rcosteira.recyclerviewexample.domain.entities.User

class UserMapper : Mapper<User, GithubUser> {
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