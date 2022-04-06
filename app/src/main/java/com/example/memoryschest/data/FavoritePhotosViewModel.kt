package com.example.memoryschest.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritePhotosViewModel(application: Application): AndroidViewModel(application) {

    private val getAllFavorites: LiveData<List<FavoritePhotos>>
    private val repository: FavoritePhotosRepository

    init {
        val favoritePhotosDao = MemoriesDatabase.getDatabase(application).favoritePhotosDao()
        repository = FavoritePhotosRepository(favoritePhotosDao)
        getAllFavorites = repository.getAllFavorites
    }

    fun addFavoritePhoto(photo: FavoritePhotos){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavoritePhoto(photo)
        }
    }
}