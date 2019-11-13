package com.example.android.horoka

import retrofit2.http.GET

private const val BASE_URL = "https://api.unsplash.com/"
private const val accessKey = R.string.accessKey

interface UnsplashApiService {
    @GET("photos/random")
    suspend fun getTodayPhoto() : HorokaPhoto
}