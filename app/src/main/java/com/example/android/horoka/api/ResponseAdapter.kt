package com.example.android.horoka.api

import com.example.android.horoka.db.HorokaPhoto
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ResponseAdapter {
    @FromJson
    fun fromJson(unsplashPhoto: UnsplashPhoto): HorokaPhoto {
        return HorokaPhoto(
            unsplashPhoto.id,
            unsplashPhoto.created_at,
            unsplashPhoto.width,
            unsplashPhoto.height,
            unsplashPhoto.color,
            unsplashPhoto.likes,
            unsplashPhoto.description,
            unsplashPhoto.alt_description,
            unsplashPhoto.urls.raw,
            unsplashPhoto.links.download_location
        )
    }

    @ToJson
    fun toJson(horokaPhoto: HorokaPhoto): UnsplashPhoto {
        return UnsplashPhoto(
            id = horokaPhoto.id,
            created_at = horokaPhoto.created_at,
            width = horokaPhoto.width,
            height = horokaPhoto.height,
            color = horokaPhoto.color,
            description = horokaPhoto.description,
            alt_description = horokaPhoto.alt_description,
            urls = UnsplashUrls(null, null, null, null, horokaPhoto.raw_url),
            links = UnsplashLinks(null, null, null, horokaPhoto.download_location),
            likes = horokaPhoto.likes
        )
    }
}