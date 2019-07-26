package com.rcosteira.core.domain.repositories

import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either

interface Repository {
    suspend fun getUsers(): Either<Failure, List<User>>


}