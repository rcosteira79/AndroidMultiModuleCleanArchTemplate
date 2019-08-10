package com.rcosteira.core.data

import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.mappers.DetailedUserMapper
import com.rcosteira.core.data.mappers.UserMapper
import com.rcosteira.core.domain.SharedFailures.NoUsers
import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.Repository
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either
import com.rcosteira.core.functional.Either.Left
import com.rcosteira.core.functional.Either.Right
import io.reactivex.Single
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val api: Api,
    private val userMapper: UserMapper,
    private val detailedUserMapper: DetailedUserMapper
) : Repository {

    override suspend fun getUsers(): Either<Failure, List<User>> {
        val users = api.getAllUsers()

        if (users.isEmpty()) {
            return Left(NoUsers())
        }

        return Right(users.map { userMapper.mapToEntity(it) })
    }

    override suspend fun getUserDetails(username: Username): Either<Failure, DetailedUser> {
        val detailedUser = api.getUserDetails(username.value)
        return Right(detailedUserMapper.mapToEntity(detailedUser));
    }

    // This Either<L,R> shenanigan really doesn't work that well with RxJava... Data related failures will already be
    // considered through the onError call of the subscriber. Would probably be able to return a Left here only if
    // some failure regarding the connection occurred or something.
    override fun rxGetUsers(): Single<List<User>> {
        return api.rxGetAllUsers()
            .map { githubUser ->
                githubUser.map { userMapper.mapToEntity(it) }
            }
    }

    override fun rxGetUserDetails(username: Username): Single<DetailedUser> {
        return api.rxGetUserDetails(username.value)
            .map { githubUserDetails ->
                detailedUserMapper.mapToEntity(githubUserDetails)
            }
    }
}