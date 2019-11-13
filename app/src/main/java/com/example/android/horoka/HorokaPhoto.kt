package com.example.android.horoka

import android.os.Parcelable
import com.unsplash.pickerandroid.photopicker.data.UnsplashLinks
import com.unsplash.pickerandroid.photopicker.data.UnsplashUrls
import com.unsplash.pickerandroid.photopicker.data.UnsplashUser
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HorokaPhoto(
    val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val color: String? = "#000000",
    val likes: Int,
    val description: String?,
    val alt_description: String?,
    val urls: UnsplashUrls,
    val links: UnsplashLinks,
    val user: UnsplashUser
) : Parcelable