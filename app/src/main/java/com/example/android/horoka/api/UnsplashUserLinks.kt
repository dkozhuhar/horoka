package com.example.android.horoka.api

import com.squareup.moshi.JsonClass

//Needed solely for generated adapter
@JsonClass(generateAdapter = true)
data class UnsplashUserLinks(
    @Transient val self: String = "https://api.unsplash.com/users/stubSelf",
    val html: String,
    @Transient val photos: String = "https://api.unsplash.com/users/georgecoletrain/photos",
    @Transient val likes: String = "https://api.unsplash.com/users/georgecoletrain/likes",
    @Transient val portfolio: String = "https://api.unsplash.com/users/georgecoletrain/portfolio",
    @Transient val following: String = "https://api.unsplash.com/users/georgecoletrain/following",
    @Transient val followers: String = "https://api.unsplash.com/users/georgecoletrain/followers"
)