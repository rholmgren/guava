package com.example.guava

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface StravaAuthService {


    @POST("oath/token")
    fun postAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("grant_type") authorizationCode: String
    ) : Response<Any>

    companion object {
        val BASE_URL = "https://www.strava.com/api/v3/oauth/token"
    }
}

data class Authorization(val token: String)
