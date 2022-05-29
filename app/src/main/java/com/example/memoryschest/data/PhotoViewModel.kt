package com.example.memoryschest.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
The ViewModel's role is to provide data to the UI and survive configuration changes.
A ViewModel acts as a communication center between the Repository and the UI.
 */

class PhotoViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Photo>>
    private val repository: PhotoRepository

    init {
        val photoDao = PhotoDatabase.getDatabase(application).photoDao()
        repository = PhotoRepository(photoDao)
        readAllData = repository.readAllData
    }

    fun addPhoto(photo: Photo) {
        // to running in a background thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPhoto(photo)
        }
    }
}