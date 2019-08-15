package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.CoroutineScopeUseCase.NoParameters
import com.rcosteira.core.interactors.SuspendUseCase
import javax.inject.Inject

class GetUsersFromApi @Inject constructor(private val usersRepository: UsersRepository) :
    SuspendUseCase<List<User>, NoParameters>() {
    override suspend fun run(params: NoParameters) = usersRepository.getUsersFromApi()
}
