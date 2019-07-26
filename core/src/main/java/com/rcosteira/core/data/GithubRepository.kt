package com.rcosteira.core.data

import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.mappers.UserMapper
import com.rcosteira.core.domain.PossibleFailures.NoUsers
import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.Repository
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either
import com.rcosteira.core.functional.Either.Left
import com.rcosteira.core.functional.Either.Right
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val api: Api,
    private val userMapper: UserMapper
) : Repository {
    override suspend fun getUsers(): Either<Failure, List<User>> {
        val users = api.getAllUsers()

        if (users.isEmpty()) {
            return Left(NoUsers())
        }

        return Right(users.map { userMapper.mapToEntity(it) })
    }
}