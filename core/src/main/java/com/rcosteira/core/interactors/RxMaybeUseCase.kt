package com.rcosteira.core.interactors

import io.reactivex.Maybe

abstract class RxMaybeUseCase<Type, in Params> where Type : Any {

    abstract fun run(params: Params): Maybe<Type>
    operator fun invoke(params: Params): Maybe<Type> = run(params)
}