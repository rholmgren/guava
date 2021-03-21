package com.example.guava.oauth.refresh

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OAuthRefreshTokenResponse(
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "expires_at")
    val expiresAt: Long,
    @Json(name = "expires_in")
    val expiresIn: Long,
    @Json(name="refresh_token")
    val refreshToken: String,
)
