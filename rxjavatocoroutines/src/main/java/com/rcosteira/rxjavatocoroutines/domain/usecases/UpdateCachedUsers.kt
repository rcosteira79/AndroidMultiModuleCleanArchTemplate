package com.rcosteira.rxjavatocoroutines.domain.usecases

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.SimpleUseCase
import javax.inject.Inject

class UpdateCachedUsers @Inject constructor(
    private val usersRepository: UsersRepository
): SimpleUseCase<Unit, List<DetailedUser>>() {
    override fun run(params: List<DetailedUser>) = usersRepository.updateCachedUsers(params)
}
