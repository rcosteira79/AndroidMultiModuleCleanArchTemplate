package com.rcosteira.recyclerviewexample.data

import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either
import com.rcosteira.core.functional.Either.Left
import com.rcosteira.core.functional.Either.Right
import com.rcosteira.recyclerviewexample.data.api.Api
import com.rcosteira.recyclerviewexample.data.mappers.UserMapper
import com.rcosteira.recyclerviewexample.domain.PossibleFailures.NoUsers
import com.rcosteira.recyclerviewexample.domain.entities.User
import com.rcosteira.recyclerviewexample.domain.repositories.Repository
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val api: Api,
    private val userMapper: UserMapper
) : Repository {
    override suspend fun getUsersAsync(): Either<Failure, List<User>> {
        val users = api.getAllUsersAsync().await()

        if (users.isEmpty()) {
            return Left(NoUsers())
        }

        return Right(users.map { userMapper.mapToEntity(it) })
    }
}