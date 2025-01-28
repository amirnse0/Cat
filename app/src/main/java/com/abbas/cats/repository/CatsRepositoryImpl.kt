package com.abbas.cats.repository

import com.abbas.cats.data.local.FavoriteItem
import com.abbas.cats.data.local.FavoriteItemLocalDataSource
import com.abbas.cats.data.remote.CatsRemoteDataSource
import com.abbas.cats.data.remote.model.CatResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatsRepositoryImpl(
    private val catsRemoteDataSource: CatsRemoteDataSource,
    private val favoriteItemLocalDataSource: FavoriteItemLocalDataSource
) : CatsRepository {
    override fun getCats(page: Int, limit: Int, containsBreed: Boolean): Flow<List<CatResponse>> = flow {
        val data = catsRemoteDataSource.getCats(
            page = page,
            limit = limit,
            containsBreeds = containsBreed
        )
        emit(data)
    }

    override suspend fun selectAsFavorite(favoriteItem: FavoriteItem) {
        favoriteItemLocalDataSource.addFavoriteItem(favoriteItem)
    }

    override suspend fun deleteFromFavorites(favoriteItem: FavoriteItem) {
        favoriteItemLocalDataSource.deleteFromFavorites(favoriteItem)
    }
}