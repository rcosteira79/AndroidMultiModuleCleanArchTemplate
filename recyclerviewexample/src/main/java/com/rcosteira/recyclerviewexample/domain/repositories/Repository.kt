package com.rcosteira.recyclerviewexample.domain.repositories

import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either
import com.rcosteira.recyclerviewexample.domain.entities.User

interface Repository {
    suspend fun getUsersAsync(): Either<Failure, List<User>>
}