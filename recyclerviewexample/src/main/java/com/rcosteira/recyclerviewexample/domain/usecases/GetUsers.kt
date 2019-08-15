package com.rcosteira.recyclerviewexample.domain.usecases

import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.CoroutineScopeUseCase
import com.rcosteira.core.interactors.CoroutineScopeUseCase.NoParameters
import javax.inject.Inject

class GetUsers @Inject constructor(private val usersRepository: UsersRepository) :
    CoroutineScopeUseCase<List<User>, NoParameters>() {
    override suspend fun run(params: NoParameters) = usersRepository.getUsers()
}
