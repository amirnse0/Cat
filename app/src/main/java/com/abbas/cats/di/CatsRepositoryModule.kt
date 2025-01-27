package com.abbas.cats.di

import com.abbas.cats.data.local.FavoriteItemLocalDataSource
import com.abbas.cats.data.remote.CatsRemoteDataSource
import com.abbas.cats.repository.CatsRepository
import com.abbas.cats.repository.CatsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class CatsRepositoryModule {
    @Provides
    fun provideCatsRepository(
        catsRemoteDataSource: CatsRemoteDataSource,
        favoriteItemLocalDataSource: FavoriteItemLocalDataSource
    ): CatsRepository = CatsRepositoryImpl(catsRemoteDataSource, favoriteItemLocalDataSource)
}