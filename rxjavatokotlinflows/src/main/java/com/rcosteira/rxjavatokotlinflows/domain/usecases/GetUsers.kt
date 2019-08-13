package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.CoroutineScopeUseCase.NoParameters
import com.rcosteira.core.interactors.SuspendEitherUseCase
import javax.inject.Inject

class GetUsers @Inject constructor(private val usersRepository: UsersRepository) :
    SuspendEitherUseCase<List<User>, NoParameters>() {
    override suspend fun run(params: NoParameters) = usersRepository.getUsersFromApi()
}
