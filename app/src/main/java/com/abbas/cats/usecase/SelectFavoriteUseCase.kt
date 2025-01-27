package com.abbas.cats.usecase

import com.abbas.cats.data.local.FavoriteItem
import com.abbas.cats.repository.CatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class SelectFavoriteUseCase @Inject constructor(
    private val catsRepository: CatsRepository
): UseCase<SelectFavoriteUseCase.Request, SelectFavoriteUseCase.Response>(
    Configuration(Dispatchers.IO)
){
    data class Request(val id: String, val isFavorite: Boolean): UseCase.Request
    class Response: UseCase.Response

    override suspend fun process(request: Request): Flow<Nothing> {
        catsRepository.selectAsFavorite(FavoriteItem(request.id, request.isFavorite))
        return emptyFlow()
    }
}