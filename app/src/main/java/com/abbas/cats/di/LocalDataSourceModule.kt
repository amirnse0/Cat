package com.abbas.cats.di

import com.abbas.cats.data.local.FavoriteItemLocalDataSource
import com.abbas.cats.data.local.FavoriteItemLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindFavoriteItemLocalDataSource(favoriteItemLocalDataSourceImpl: FavoriteItemLocalDataSourceImpl): FavoriteItemLocalDataSource
}