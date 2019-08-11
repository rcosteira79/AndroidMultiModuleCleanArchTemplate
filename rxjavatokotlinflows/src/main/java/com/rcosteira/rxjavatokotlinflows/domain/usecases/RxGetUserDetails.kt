package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.RxSingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class RxGetUserDetails @Inject constructor(
    private val usersRepository: UsersRepository
) : RxSingleUseCase<DetailedUser, Username>() {
    override fun run(params: Username): Single<DetailedUser> = usersRepository.rxGetUserDetails(params)
}