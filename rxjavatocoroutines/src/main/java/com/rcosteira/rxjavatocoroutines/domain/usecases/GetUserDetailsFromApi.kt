package com.rcosteira.rxjavatocoroutines.domain.usecases

import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.SuspendUseCase
import javax.inject.Inject

class GetUserDetailsFromApi @Inject constructor(private val usersRepository: UsersRepository) :
    SuspendUseCase<DetailedUser, Username>() {
    override suspend fun run(params: Username) = usersRepository.getUserDetailsFromApi(params)
}
