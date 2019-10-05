package com.rcosteira.core.interactors

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<Type, in Params> where Type : Any {

    abstract fun run(params: Params): Flow<Type>
    operator fun invoke(params: Params): Flow<Type> = run(params)
}