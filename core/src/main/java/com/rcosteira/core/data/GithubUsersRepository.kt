package com.rcosteira.core.data

import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.cache.Cache
import com.rcosteira.core.data.mappers.DetailedUserMapper
import com.rcosteira.core.data.mappers.UserMapper
import com.rcosteira.core.domain.SharedFailures.NoUsers
import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.extensions.mapListElements
import com.rcosteira.core.functional.Either
import com.rcosteira.core.functional.Either.Left
import com.rcosteira.core.functional.Either.Right
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import javax.inject.Inject

class GithubUsersRepository @Inject constructor(
    private val api: Api,
    private val cache: Cache,
    private val userMapper: UserMapper,
    private val detailedUserMapper: DetailedUserMapper
) : UsersRepository {

    override suspend fun getUsersFromApi(): Either<Failure, List<User>> {
        val users = api.getAllUsers()

        if (users.isEmpty()) {
            return Left(NoUsers())
        }

        return Right(users.map { userMapper.mapToEntity(it) })
    }

    override suspend fun getUserDetailsFromApi(username: Username): Either<Failure, DetailedUser> {
        val detailedUser = api.getUserDetails(username.value)
        return Right(detailedUserMapper.mapToEntity(detailedUser));
    }

    override suspend fun getCachedUsers(): Either<Failure, List<DetailedUser>> {
        val users = cache.getAllUsers()

        if (users.isEmpty()) {
            return Left(NoUsers())
        }

        return Right(users.map { detailedUserMapper.mapToEntity(it) })
    }

    // This Either<L,R> shenanigan really doesn't work that well with RxJava... Data related failures will already be
    // considered through the onError call of the subscriber. Would probably be able to return left Left here only if
    // some failure regarding the connection occurred or something, before even performing the api call.
    override fun rxGetUsersFromApi(): Observable<User> {
        return api.rxGetAllUsers() // we use Maybe for semantic purposes - we only get one response on each api request.
            .flattenAsObservable { it } // However, transformations are easier with Observables :)
            .map { userMapper.mapToEntity(it) }
    }

    override fun rxGetUserDetailsFromApi(username: Username): Maybe<DetailedUser> {
        return api.rxGetUserDetails(username.value)
            .map { detailedUserMapper.mapToEntity(it) }
    }

    override fun rxGetCachedUsers(): Flowable<List<DetailedUser>> {
        return cache.rxGetAllUsers()
            .distinctUntilChanged()
            .filter { it.isNotEmpty() }
            .mapListElements { detailedUserMapper.mapToEntity(it) } // extension function
    }

    override fun updateCachedUsers(users: List<DetailedUser>) {
        cache.updateCachedUsers(users.map { detailedUserMapper.mapFromEntity(it) })
    }
}