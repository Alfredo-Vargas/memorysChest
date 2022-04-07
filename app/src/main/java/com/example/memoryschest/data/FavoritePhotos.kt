package com.example.memoryschest.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class FavoritePhotos  (
    @PrimaryKey(autoGenerate = true)
    val photoID: Int,
    val photoName: String,
    val dateTaken: String,
    val location: String

)