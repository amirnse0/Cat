package com.abbas.cats.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CatFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewItem(favoriteItem: FavoriteItem)

    @Query("SELECT * FROM favorite_item")
    fun getAllFavoriteItems(): Flow<List<FavoriteItem>>

    @Delete
    suspend fun deleteFromFavorites(favoriteItem: FavoriteItem)

    @Query("SELECT * FROM favorite_item WHERE :id == id")
    suspend fun getFavoriteId(id: String): String?

}