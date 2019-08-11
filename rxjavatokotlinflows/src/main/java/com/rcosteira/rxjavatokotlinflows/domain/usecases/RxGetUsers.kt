package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.RxSingleUseCase
import com.rcosteira.core.interactors.UseCase.None
import io.reactivex.Single
import javax.inject.Inject

class RxGetUsers @Inject constructor(
    private val usersRepository: UsersRepository
) : RxSingleUseCase<List<User>, None>() {
    override fun run(params: None): Single<List<User>> = usersRepository.rxGetUsers()
}