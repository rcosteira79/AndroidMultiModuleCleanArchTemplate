package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.RxMaybeUseCase
import io.reactivex.Maybe
import javax.inject.Inject

class RxGetUserDetails @Inject constructor(
    private val usersRepository: UsersRepository
) : RxMaybeUseCase<DetailedUser, Username>() {
    override fun run(params: Username): Maybe<DetailedUser> = usersRepository.rxGetUserDetailsFromApi(params)
}