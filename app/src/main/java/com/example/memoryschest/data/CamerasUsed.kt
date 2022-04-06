package com.example.memoryschest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CamerasUsed  (
    @PrimaryKey(autoGenerate = true)
    val cameraModel: String,
    val cameraOwner: String

)