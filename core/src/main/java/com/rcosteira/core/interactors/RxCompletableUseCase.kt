package com.rcosteira.core.interactors

import io.reactivex.Completable

abstract class RxCompletableUseCase<in Params> {

    abstract fun run(params: Params): Completable
    operator fun invoke(params: Params): Completable = run(params)
}

