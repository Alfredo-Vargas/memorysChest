package com.example.memoryschest.data

import androidx.lifecycle.LiveData

class FavoritePhotosRepository(private val favoritePhotosDao: FavoritePhotosDao) {
    val getAllFavorites: LiveData<List<FavoritePhotos>> = favoritePhotosDao.getAllFavoritePhotos()

    suspend fun addFavoritePhoto(photo: FavoritePhotos) {
        favoritePhotosDao.addPhotoToFavorites(photo)
    }


}