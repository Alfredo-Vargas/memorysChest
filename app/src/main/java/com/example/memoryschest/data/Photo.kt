package com.example.memoryschest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class Photo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val geolocation: String,
    val camera: String
)
