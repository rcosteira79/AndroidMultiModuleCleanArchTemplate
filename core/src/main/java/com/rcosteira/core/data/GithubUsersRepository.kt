package com.rcosteira.core.data

import com.rcosteira.core.data.api.Api
import com.rcosteira.core.data.cache.Cache
import com.rcosteira.core.data.mappers.DetailedUserMapper
import com.rcosteira.core.data.mappers.UserMapper
import com.rcosteira.core.domain.Id
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
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubUsersRepository @Inject constructor(
    private val api: Api,
    private val cache: Cache,
    private val userMapper: UserMapper,
    private val detailedUserMapper: DetailedUserMapper
) : UsersRepository {
    /*************************** Used in RecyclerViewExample module ***************************/
    override suspend fun getUsers(): Either<Failure, List<User>> {
        val users = api.getAllUsers()

        if (users.isEmpty()) {
            return Left(NoUsers())
        }

        return Right(users.map { userMapper.mapToEntity(it) })
    }
    /*************************** Used in RecyclerViewExample module ***************************/


    /*************************** Used in RxJavaToKotlinFlows module ***************************/
    override suspend fun getUsersFromApi(): List<User> {
        return api.getAllUsers()
            .map { userMapper.mapToEntity(it) }
    }

    override suspend fun getUserDetailsFromApi(username: Username): DetailedUser {
        val detailedUser = api.getUserDetails(username.value)
        return detailedUserMapper.mapToEntity(detailedUser)
    }

    override fun getCachedUsers(): Flow<List<DetailedUser>> {
        return cache.getAllUsers()
            .map { userList ->
                userList.map { detailedUserMapper.mapToEntity(it) }
            }
    }

    override fun rxGetUsersFromApi(): Observable<User> {
        return api.rxGetAllUsers() // Maybe for semantic purposes - one possible response on each request.
            .flattenAsObservable { it } // However, we need to transform each element of the list
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

    override fun rxDeleteCachedUser(id: Id): Completable {
        return cache.rxDeleteCachedUser(id)
    }
}