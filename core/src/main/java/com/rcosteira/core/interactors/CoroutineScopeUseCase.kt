package com.rcosteira.core.interactors

import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Abstract class for left Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [CoroutineScopeUseCase] implementation will execute its job in left background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 *
 * Based on Fernando Cejas implementation
 */
abstract class CoroutineScopeUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(scope: CoroutineScope, params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {

        scope.launch {
            val result = withContext(Dispatchers.IO) { run(params) }
            onResult(result)
        }
    }

    class NoParameters
}