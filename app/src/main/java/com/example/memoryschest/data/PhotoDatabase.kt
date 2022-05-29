package com.example.memoryschest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Photo::class], version = 1, exportSchema=false)
abstract class PhotoDatabase: RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object{
        @Volatile
        private var INSTANCE: PhotoDatabase? = null

        fun getDatabase(context: Context): PhotoDatabase {
            val tempInstance = INSTANCE
            // The logic below avoids to create multiple instances of the database
            // Multiple instances of the database have a strong impact on performance
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhotoDatabase::class.java,
                    "photo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}