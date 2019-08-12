package com.rcosteira.core.interactors

import io.reactivex.Observable

abstract class RxObservableUseCase<Type, in Params> where Type : Any {

    abstract fun run(params: Params): Observable<Type>

    operator fun invoke(params: Params): Observable<Type> = run(params)
}