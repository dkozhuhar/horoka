package com.example.android.horoka.api


import com.example.android.horoka.db.HorokaPhoto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.unsplash.com/"

val moshi = Moshi.Builder()
    .add(ResponseAdapter())
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

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

val apiService: UnsplashApiService by lazy {
    retrofit.create(UnsplashApiService::class.java)
}