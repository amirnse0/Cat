package com.abbas.cats.usecase

import com.abbas.cats.data.local.FavoriteItem
import com.abbas.cats.repository.CatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val catsRepository: CatsRepository
): UseCase<DeleteFavoriteUseCase.Request, DeleteFavoriteUseCase.Response>(
    Configuration(Dispatchers.IO)
){
    data class Request(val id: String): UseCase.Request
    class Response: UseCase.Response

    override suspend fun process(request: Request): Flow<Nothing> {
        catsRepository.deleteFromFavorites(FavoriteItem(request.id))
        return emptyFlow()
    }
}