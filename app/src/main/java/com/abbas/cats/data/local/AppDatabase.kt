package com.abbas.cats.data.local

import androidx.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteItem::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun catFavoriteDao(): CatFavoriteDao

    companion object{
        @Volatile
        private var INSTANCE:AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            } else{
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database",
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}