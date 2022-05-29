package com.example.memoryschest.data

import androidx.lifecycle.LiveData

/*
A repository class abstract access to multiple data sources.
The repository is not part of the Architecture Components libraries,
but is a suggested best practice for code separation and clean architecture
 */

class PhotoRepository(private val photoDao: PhotoDao) {
    val readAllData: LiveData<List<Photo>> = photoDao.readAllData()

    suspend fun addPhoto(photo: Photo) {
        photoDao.addPhoto(photo)
    }
}