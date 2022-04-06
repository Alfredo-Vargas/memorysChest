package com.example.memoryschest.data

import androidx.lifecycle.LiveData

class CamerasUsedRepository(private val camerasUsedDao: CamerasUsedDao) {
    val getAllCameras: LiveData<List<CamerasUsed>> = camerasUsedDao.getAllCamerasUsed()

    suspend fun addCamera(camera: CamerasUsed) {
        camerasUsedDao.addCameraUsed(camera)
    }


}