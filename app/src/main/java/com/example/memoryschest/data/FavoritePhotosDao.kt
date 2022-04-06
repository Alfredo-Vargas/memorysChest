package com.example.memoryschest.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritePhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // suspend keyword used, because is gonna be handle by the coroutines
    suspend fun addPhotoToFavorites(photo: FavoritePhotos)

    @Query("SELECT * FROM FavoritePhotos ORDER BY photoName ASC")
    fun getAllFavoritePhotos(): LiveData<List<FavoritePhotos>>
}