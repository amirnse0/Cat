package com.abbas.cats.usecase

import com.abbas.cats.repository.CatsRepository
import com.abbas.cats.usecase.converter.CatConverter
import com.abbas.cats.usecase.presentationmodel.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val catsRepository: CatsRepository
) : UseCase<GetCatsUseCase.Request, GetCatsUseCase.Response>(
    Configuration(Dispatchers.IO)
) {
    data class Request(
        val page: Int,
        val limit: Int,
        val containsBreed: Boolean = true
    ) : UseCase.Request

    data class Response(val data: List<Cat>) : UseCase.Response

    override suspend fun process(request: Request): Flow<Response> {
        return catsRepository.getCats(request.page, request.limit, request.containsBreed)
            .map { Response(it) }
    }
}