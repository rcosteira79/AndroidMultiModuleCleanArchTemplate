package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.DetailedUser
import com.rcosteira.core.domain.Username
import com.rcosteira.core.domain.repositories.Repository
import com.rcosteira.core.interactors.RxSingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class RxGetUserDetails @Inject constructor(
    private val repository: Repository
) : RxSingleUseCase<DetailedUser, Username>() {
    override fun run(params: Username): Single<DetailedUser> = repository.rxGetUserDetails(params)
}