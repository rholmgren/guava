package com.example.guava.oauth

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface OAuthService {
    @POST("oauth/token")
    suspend fun requestAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("grant_type") grantType: String,
        @Query("code") code: String
    ) : Response<OAuthAccessTokenResponse>

    @POST("oauth/token")
    suspend fun refreshToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("grant_type") grantType: String,
        @Query("refresh_token") refreshToken: String
    ) : Response<OAuthRefreshTokenResponse>

    companion object {
        val BASE_URL = "https://www.strava.com/api/v3/oauth/token"
    }
}

