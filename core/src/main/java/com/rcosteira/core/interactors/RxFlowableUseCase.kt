package com.rcosteira.core.interactors

import io.reactivex.Flowable

abstract class RxFlowableUseCase<Type, in Params> where Type : Any {

    abstract fun run(params: Params): Flowable<Type>
    operator fun invoke(params: Params): Flowable<Type> = run(params)
}
