package com.abbas.cats.data

import com.abbas.cats.data.model.CatResponse

interface CatsRemoteDataSource {
    suspend fun getCats(
        page: Int,
        limit: Int,
        containsBreeds: Boolean = true
    ): List<CatResponse>
}