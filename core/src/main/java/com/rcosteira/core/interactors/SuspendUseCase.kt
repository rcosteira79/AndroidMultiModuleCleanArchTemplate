package com.rcosteira.core.interactors

// Simple use case for the cases where threading is controlled by the caller.
abstract class SuspendUseCase<Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type
    suspend operator fun invoke(params: Params): Type = run(params)
}