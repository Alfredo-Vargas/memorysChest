package com.example.memoryschest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// It is a good practice to exportSchema -> then TODO: change to true and export the schema
@Database(entities = [FavoritePhotos::class, CamerasUsed::class], version = 1, exportSchema = false)
abstract class MemoriesDatabase: RoomDatabase() {
    abstract fun favoritePhotosDao(): FavoritePhotosDao
    companion object {
        @Volatile   // writes are immediately visible to other threads
        private var INSTANCE: MemoriesDatabase? = null  // singleton class (only one instance of it)

        fun getDatabase(context: Context): MemoriesDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemoriesDatabase::class.java,
                    "memories_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}