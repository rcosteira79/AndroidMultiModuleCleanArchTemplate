package com.rcosteira.core.interactors

import com.rcosteira.core.exception.Failure
import com.rcosteira.core.functional.Either

/** Simple use case for the cases where threading is controlled by the caller.
 *  Uses the Either construct to help determine if the operation was successful or not.
 *  Has to be invoked either (pun intended) from left coroutine or left suspend function.
 */
abstract class SuspendEitherUseCase<Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>
    suspend operator fun invoke(params: Params): Either<Failure, Type> = run(params)
}