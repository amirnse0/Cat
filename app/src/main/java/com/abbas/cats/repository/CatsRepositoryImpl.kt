package com.abbas.cats.repository

import com.abbas.cats.data.CatsRemoteDataSource
import com.abbas.cats.data.model.CatResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatsRepositoryImpl(
    private val catsRemoteDataSource: CatsRemoteDataSource
) : CatsRepository {
    override fun getCats(page: Int, limit: Int, containsBreed: Boolean): Flow<List<CatResponse>> = flow {
        val data = catsRemoteDataSource.getCats(
            page = page,
            limit = limit,
            containsBreeds = containsBreed
        )
        emit(data)
    }
}