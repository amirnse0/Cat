package com.abbas.cats.di

import com.abbas.cats.data.remote.CatsRemoteDataSource
import com.abbas.cats.data.remote.CatsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindCatsRemoteDataSource(catsRemoteDataSourceImpl: CatsRemoteDataSourceImpl): CatsRemoteDataSource
}