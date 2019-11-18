package com.example.android.horoka.api

import com.example.android.horoka.db.HorokaPhoto
import com.squareup.moshi.FromJson

class ResponseAdapter {
    @FromJson fun HorokaPhotoFromJson(unsplashPhoto: UnsplashPhoto): HorokaPhoto {
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
}