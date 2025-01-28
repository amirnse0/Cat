package com.abbas.cats.usecase

import com.abbas.cats.repository.CatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsItemFavoriteUseCase @Inject constructor(
    private val catsRepository: CatsRepository
) : UseCase<IsItemFavoriteUseCase.Request, IsItemFavoriteUseCase.Response>(
    Configuration(Dispatchers.IO)
) {
    data class Request(val id: String) : UseCase.Request

    data class Response(val isFavorite: Boolean) : UseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return flow { Response(catsRepository.isItemFavorite(id = request.id)) }
    }
}