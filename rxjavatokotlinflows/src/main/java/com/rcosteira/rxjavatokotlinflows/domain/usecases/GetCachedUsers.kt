package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either
import com.rcosteira.core.interactors.CoroutineScopeUseCase.NoParameters
import com.rcosteira.core.interactors.SuspendEitherUseCase
import javax.inject.Inject

class GetCachedUsers @Inject constructor(
    private val usersRepository: UsersRepository
) : SuspendEitherUseCase<List<DetailedUser>, NoParameters>() {
    override suspend fun run(params: NoParameters): Either<Failure, List<DetailedUser>> =
        usersRepository.getCachedUsers()
}