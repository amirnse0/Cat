package com.abbas.cats.repository

import com.abbas.cats.data.local.FavoriteItem
import com.abbas.cats.usecase.presentationmodel.Cat
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getCats(
        page: Int,
        limit: Int,
        containsBreed: Boolean
    ): Flow<List<Cat>>

    suspend fun selectAsFavorite(
        favoriteItem: FavoriteItem
    )

    suspend fun deleteFromFavorites(favoriteItem: FavoriteItem)

    fun getFavoriteItems(): Flow<List<FavoriteItem>>

    suspend fun isItemFavorite(id: String): Boolean
}