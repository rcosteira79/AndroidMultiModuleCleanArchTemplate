package com.rcosteira.rxjavatocoroutines.domain.usecases

import com.rcosteira.core.domain.entities.User
import com.rcosteira.core.domain.repositories.UsersRepository
import com.rcosteira.core.interactors.CoroutineScopeUseCase.NoParameters
import com.rcosteira.core.interactors.RxObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class RxGetUsersFromApi @Inject constructor(
    private val usersRepository: UsersRepository
) : RxObservableUseCase<User, NoParameters>() {
    override fun run(params: NoParameters): Observable<User> = usersRepository.rxGetUsersFromApi()
}