package com.example.android.horoka.api

import com.squareup.moshi.JsonClass

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
    val user: UnsplashUser,
    @Transient val exif: String = "stubExif",
    @Transient val location: String = "stubLocation"
)
