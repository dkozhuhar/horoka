package com.example.android.horoka


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HorokaPhoto(
    @PrimaryKey val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val color: String? = "#000000",
    val likes: Int,
    val description: String?,
    val alt_description: String?,
    val urls: String,
    val download_location: String
)