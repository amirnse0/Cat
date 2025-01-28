package com.abbas.cats.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteItemLocalDataSourceImpl @Inject constructor(
    private val catFavoriteDao: CatFavoriteDao
): FavoriteItemLocalDataSource {
    override suspend fun addFavoriteItem(favoriteItem: FavoriteItem) {
        catFavoriteDao.insertNewItem(favoriteItem)
    }

    override fun getFavoriteItems(): Flow<List<FavoriteItem>> =
        catFavoriteDao.getAllFavoriteItems()

    override suspend fun deleteFromFavorites(favoriteItem: FavoriteItem) {
        catFavoriteDao.deleteFromFavorites(favoriteItem)
    }

    override suspend fun isItemFavorite(id: String): Boolean =
        !catFavoriteDao.getFavoriteId(id).isNullOrBlank()
}