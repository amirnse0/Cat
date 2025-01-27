package com.abbas.cats.data.remote

import com.abbas.cats.data.remote.model.CatResponse

interface CatsRemoteDataSource {
    suspend fun getCats(
        page: Int,
        limit: Int,
        containsBreeds: Boolean = true
    ): List<CatResponse>
}