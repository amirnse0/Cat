package com.abbas.cats.di

import android.content.Context
import com.abbas.cats.data.local.AppDatabase
import com.abbas.cats.data.local.CatFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getDatabase(context)

    @Provides
    fun provideCatFavoriteDao(appDatabase: AppDatabase): CatFavoriteDao =
        appDatabase.catFavoriteDao()
}