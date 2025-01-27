package com.abbas.cats.repository

import com.abbas.cats.data.local.FavoriteItem
import com.abbas.cats.data.remote.model.CatResponse
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getCats(
        page: Int,
        limit: Int,
        containsBreed: Boolean
    ): Flow<List<CatResponse>>

    suspend fun selectAsFavorite(
        favoriteItem: FavoriteItem
    )
}