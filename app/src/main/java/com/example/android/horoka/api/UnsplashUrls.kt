package com.example.android.horoka.api

import com.squareup.moshi.JsonClass

//Needed solely for generated adapter
@JsonClass(generateAdapter = true)
data class UnsplashUrls(
    val thumb: String?,
    val small: String?,
    val regular: String?,
    val full: String?,
    val raw: String
)