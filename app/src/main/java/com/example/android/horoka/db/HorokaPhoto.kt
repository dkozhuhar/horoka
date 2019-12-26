package com.example.android.horoka.db


import androidx.room.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.Instant

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
    val added_datetime: Long
)