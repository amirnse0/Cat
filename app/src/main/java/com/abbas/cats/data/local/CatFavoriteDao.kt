package com.abbas.cats.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CatFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewItem(favoriteItem: FavoriteItem)

    @Query("SELECT * FROM favorite_item")
    fun getAllFavoriteItems(): Flow<FavoriteItem>

}