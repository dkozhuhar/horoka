package com.example.android.horoka.api

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

//Transient fields are fields I don't care about
@JsonClass(generateAdapter = true)
data class UnsplashPhoto (
    val id: String,
    val created_at: String,
    @Transient val updated_at: String = "stubUpdatedAt",
    @Transient val promoted_at: String = "stubPromotedAt",
    val width: Int,
    val height: Int,
    val color: String? = "#000000",
    val description: String?,
    val alt_description: String?,
    val urls: UnsplashUrls,
    val links: UnsplashLinks,
    @Transient val categories: List<String> = arrayListOf(""),
    val likes: Int,
    @Transient val liked_by_user: Boolean = false,
    @Transient val current_user_collections: List<String> = arrayListOf(""),
    @Transient val user: String = "stubUser",
    @Transient val exif: String = "stubExif",
    @Transient val location: String = "stubLocation"
)
