package com.rcosteira.rxjavatocoroutines.domain.usecases

import com.rcosteira.core.domain.Id
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.RxCompletableUseCase
import javax.inject.Inject

class RxDeleteCachedUser @Inject constructor(
    private val usersRepository: UsersRepository
) : RxCompletableUseCase<Id>() {
    override fun run(params: Id) = usersRepository.rxDeleteCachedUser(params)
}
