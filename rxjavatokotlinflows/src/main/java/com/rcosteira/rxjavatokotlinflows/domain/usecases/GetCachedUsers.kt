package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.CoroutineScopeUseCase.NoParameters
import com.rcosteira.core.interactors.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCachedUsers @Inject constructor(
    private val usersRepository: UsersRepository
) : FlowUseCase<List<DetailedUser>, NoParameters>() {
    override suspend fun run(params: NoParameters): Flow<List<DetailedUser>> = usersRepository.getCachedUsers()
}