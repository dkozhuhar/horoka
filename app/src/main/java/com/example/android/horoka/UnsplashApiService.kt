package com.example.android.horoka


import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.unsplash.com/"


interface UnsplashApiService {
    @GET("photos/random")
    suspend fun getTodayPhoto(
        @Query("client_id") clientId: String,
        @Query("query") criteria: String
    ): HorokaPhoto

    @GET("photos/random")
    suspend fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("query") criteria: String,
        @Query("count") numberOfPhotos: Int
    ): List<HorokaPhoto>
}