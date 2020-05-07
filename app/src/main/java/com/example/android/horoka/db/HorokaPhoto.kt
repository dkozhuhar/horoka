package com.example.android.horoka.db


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.horoka.api.UnsplashUserLinks
import com.squareup.moshi.Json

@Entity(tableName = "photos")
data class HorokaPhoto(
    @PrimaryKey val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val color: String? = "#000000",
    val likes: Int,
    val description: String?,
    val alt_description: String?,
    @Json(name = "urls")     val raw_url: String,
    @Json(name = "links") val download_location: String,
    //    Added datetime in Unix time format
    val added_datetime: Long,
    val user_name: String,
    val user_link: String
)