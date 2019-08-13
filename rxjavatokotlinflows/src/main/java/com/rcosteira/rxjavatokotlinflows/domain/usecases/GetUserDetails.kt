package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either
import com.rcosteira.core.interactors.SuspendEitherUseCase
import javax.inject.Inject

class GetUserDetails @Inject constructor(private val usersRepository: UsersRepository) :
    SuspendEitherUseCase<DetailedUser, Username>() {
    override suspend fun run(params: Username): Either<Failure, DetailedUser> =
        usersRepository.getUserDetailsFromApi(params)
}
