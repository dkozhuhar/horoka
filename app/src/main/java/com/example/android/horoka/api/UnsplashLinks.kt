package com.example.android.horoka.api

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

//Needed solely for generated adapter
@JsonClass(generateAdapter = true)
data class UnsplashLinks (
    val self: String?,
    val html: String?,
    val download: String?,
    val download_location: String
)
