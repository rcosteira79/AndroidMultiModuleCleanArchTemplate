package com.rcosteira.core.domain.repositories

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either
import io.reactivex.Single

interface UsersRepository {
    suspend fun getUsers(): Either<Failure, List<User>>
    suspend fun getUserDetails(username: Username): Either<Failure, DetailedUser>

    fun rxGetUsers(): Single<List<User>>
    fun rxGetUserDetails(username: Username): Single<DetailedUser>
}