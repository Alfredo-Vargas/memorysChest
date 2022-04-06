package com.example.memoryschest.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CamerasUsedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // suspend keyword used, because is gonna be handle by the coroutines
    suspend fun addCameraUsed(camera: CamerasUsed)

    @Query("SELECT * FROM CamerasUsed ORDER BY cameraModel ASC")
    fun getAllCamerasUsed(): LiveData<List<CamerasUsed>>
}