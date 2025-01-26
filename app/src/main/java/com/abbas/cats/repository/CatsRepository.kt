package com.abbas.cats.repository

import com.abbas.cats.data.model.CatResponse
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getCats(
        page: Int,
        limit: Int,
        containsBreed: Boolean
    ): Flow<List<CatResponse>>
}