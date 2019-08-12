package com.rcosteira.core.interactors

// Simple use case for the cases where threading is controlled by the caller
abstract class SimpleUseCase<Type, in Params> where Type : Any {

    abstract fun run(params: Params): Type
    operator fun invoke(params: Params): Type = run(params)
}