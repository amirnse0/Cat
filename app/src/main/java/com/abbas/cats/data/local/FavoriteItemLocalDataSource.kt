package com.abbas.cats.data.local

import kotlinx.coroutines.flow.Flow

interface FavoriteItemLocalDataSource {
    suspend fun addFavoriteItem(favoriteItem: FavoriteItem)
    fun getFavoriteItems(): Flow<List<FavoriteItem>>
    suspend fun deleteFromFavorites(favoriteItem: FavoriteItem)
}