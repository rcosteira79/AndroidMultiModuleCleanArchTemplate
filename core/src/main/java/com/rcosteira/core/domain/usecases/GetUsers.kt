package com.rcosteira.core.domain.usecases

import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.UseCase
import com.rcosteira.core.interactors.UseCase.None
import javax.inject.Inject

class GetUsers @Inject constructor(private val usersRepository: UsersRepository) :
    UseCase<List<User>, None>() {
    override suspend fun run(params: None) = usersRepository.getUsersFromApi()
}
