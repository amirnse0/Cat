package com.abbas.cats.data.remote

import com.abbas.cats.data.remote.model.CatResponse
import javax.inject.Inject

class CatsRemoteDataSourceImpl @Inject constructor(
    private val catsService: CatsService
) : CatsRemoteDataSource {
    override suspend fun getCats(page: Int, limit: Int, containsBreeds: Boolean): List<CatResponse> {
        return catsService.getCats(
            page = page,
            limit = limit,
            hasBreeds = containsBreeds
        )
    }
}