package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.RxFlowableUseCase
import com.rcosteira.core.interactors.CoroutineScopeUseCase.NoParameters
import io.reactivex.Flowable
import javax.inject.Inject

class RxGetCachedUsers @Inject constructor(
    private val usersRepository: UsersRepository
) : RxFlowableUseCase<List<DetailedUser>, NoParameters>() {
    override fun run(params: NoParameters): Flowable<List<DetailedUser>> = usersRepository.rxGetCachedUsers()
}