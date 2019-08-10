package com.rcosteira.core.interactors

import io.reactivex.Single

abstract class RxSingleUseCase<Type, in Params> where Type : Any {

    abstract fun run(params: Params): Single<Type>

    operator fun invoke(params: Params): Single<Type> {
        return run(params)
    }
}