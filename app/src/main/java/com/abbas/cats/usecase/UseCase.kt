package com.abbas.cats.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class UseCase<T : UseCase.Request, R : UseCase.Response>(
    private val configuration: Configuration
) {
    interface Request

    interface Response

    internal abstract suspend fun process(request: T): Flow<R>

    suspend fun execute(request: T) = process(request).map {
        Result.Success(it) as Result<R>
    }.flowOn(configuration.dispatcher)
        .catch {
            emit(Result.Error(it))
        }
}