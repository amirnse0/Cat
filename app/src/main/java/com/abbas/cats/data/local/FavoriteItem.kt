package com.abbas.cats.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo as CI

@Entity(tableName = "favorite_item")
data class FavoriteItem(
    @PrimaryKey
    val id: String
)