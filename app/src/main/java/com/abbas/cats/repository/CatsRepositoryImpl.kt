package com.abbas.cats.repository

import com.abbas.cats.data.local.FavoriteItem
import com.abbas.cats.data.local.FavoriteItemLocalDataSource
import com.abbas.cats.data.remote.CatsRemoteDataSource
import com.abbas.cats.usecase.converter.CatConverter
import com.abbas.cats.usecase.presentationmodel.Cat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class CatsRepositoryImpl(
    private val catsRemoteDataSource: CatsRemoteDataSource,
    private val favoriteItemLocalDataSource: FavoriteItemLocalDataSource
) : CatsRepository {

    private fun getCatsFromServer(page: Int, limit: Int, containsBreed: Boolean): Flow<List<Cat>> = flow {
        val data = catsRemoteDataSource.getCats(
            page = page,
            limit = limit,
            containsBreeds = containsBreed
        )
        val cats = data.map { CatConverter.convertPresentationCat(it) }
        emit(cats)
    }

    private fun getFavoritesFromLocal(): Flow<List<FavoriteItem>> =
        favoriteItemLocalDataSource.getFavoriteItems()

    private fun mergeFlows(
        catsFlow: Flow<List<Cat>>,
        favoriteFlow: Flow<List<FavoriteItem>>
    ): Flow<List<Cat>> {
        return catsFlow.combine(favoriteFlow){ cats, favorites ->
            val favoriteMap = favorites.associateBy { it.id }
            cats.map { cat ->
                Cat(
                    id = cat.id,
                    name = cat.name,
                    description = cat.description,
                    origin = cat.origin,
                    temperament = cat.temperament,
                    lifeSpan = cat.lifeSpan,
                    image = cat.image,
                    isFavorite = favoriteMap[cat.id] != null
                )
            }
        }
    }

    override fun getCats(page: Int, limit: Int, containsBreed: Boolean): Flow<List<Cat>> =
        mergeFlows(
            catsFlow = getCatsFromServer(page, limit, containsBreed),
            favoriteFlow = getFavoritesFromLocal()
        )

    override suspend fun selectAsFavorite(favoriteItem: FavoriteItem) {
        favoriteItemLocalDataSource.addFavoriteItem(favoriteItem)
    }

    override suspend fun deleteFromFavorites(favoriteItem: FavoriteItem) {
        favoriteItemLocalDataSource.deleteFromFavorites(favoriteItem)
    }

    override fun getFavoriteItems(): Flow<List<FavoriteItem>> =
        favoriteItemLocalDataSource.getFavoriteItems()
}