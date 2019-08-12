package com.rcosteira.rxjavatokotlinflows.domain.usecases

import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.RxObservableUseCase
import com.rcosteira.core.interactors.UseCase.None
import io.reactivex.Observable
import javax.inject.Inject

class RxGetUsersFromApi @Inject constructor(
    private val usersRepository: UsersRepository
) : RxObservableUseCase<User, None>() {
    override fun run(params: None): Observable<User> = usersRepository.rxGetUsersFromApi()
}