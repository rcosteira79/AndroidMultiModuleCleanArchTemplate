package com.rcosteira.core.domain.repositories

import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(): Either<Failure, List<User>>

    suspend fun getUsersFromApi(): List<User>
    suspend fun getUserDetailsFromApi(username: Username): DetailedUser
    fun getCachedUsers(): Flow<List<DetailedUser>>

    fun rxGetUsersFromApi(): Observable<User>
    fun rxGetUserDetailsFromApi(username: Username): Maybe<DetailedUser>
    fun rxGetCachedUsers(): Flowable<List<DetailedUser>>

    fun updateCachedUsers(users: List<DetailedUser>)
}