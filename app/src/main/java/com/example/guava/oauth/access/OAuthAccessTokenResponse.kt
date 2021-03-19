package com.example.guava.oauth.access

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OAuthAccessTokenResponse(
    @Json(name = "token_type")
    val tokenType: String,
    @Json(name = "expires_at")
    val expiresAt: Long,
    @Json(name = "expires_in")
    val expiresIn: Long,
    @Json(name = "refresh_token")
    val refreshToken: String,
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "athlete")
    val athlete: AthleteSummaryInfo
)

@JsonClass(generateAdapter = true)
data class AthleteSummaryInfo(
    @Json(name = "id")
    val id: String,
    @Json(name = "username")
    val username: String
)