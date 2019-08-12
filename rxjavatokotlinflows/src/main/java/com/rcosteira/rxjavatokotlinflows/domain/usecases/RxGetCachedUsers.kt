package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.RxFlowableUseCase
import com.rcosteira.core.interactors.UseCase.None
import io.reactivex.Flowable
import javax.inject.Inject

class RxGetCachedUsers @Inject constructor(
    private val usersRepository: UsersRepository
) : RxFlowableUseCase<List<DetailedUser>, None>() {
    override fun run(params: None): Flowable<List<DetailedUser>> = usersRepository.rxGetCachedUsers()
}