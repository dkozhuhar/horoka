package com.example.android.horoka.api

import com.squareup.moshi.JsonClass

//Needed solely for generated adapter
@JsonClass(generateAdapter = true)
data class UnsplashUser(
    @Transient val id: String = "stubId",
    @Transient val updated_at: String = "stubUpdatedAt",
    @Transient val username: String = "stubUsername",
    val name: String,
    @Transient val first_name: String = "stubFirstName",
    @Transient val last_name: String = "stubLastName",
    @Transient val twitter_username: String = "stubTwitterUsername",
    @Transient val portfolio_url: String = "stubPortfolioUrl",
    @Transient val bio: String = "stubBio",
    @Transient val location: String = "stubLocation",
    val links: UnsplashUserLinks,
    @Transient val profile_image: String = "stubProfileImage",
    @Transient val instagram_username: String = "stubInstagramUsername",
    @Transient val total_collections: Int = 0,
    @Transient val total_likes: Int = 26,
    @Transient val total_photos: Int = 23,
    @Transient val accepted_tos: Boolean = true
)

