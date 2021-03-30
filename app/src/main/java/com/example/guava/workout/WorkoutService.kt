package com.example.guava.workout

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WorkoutService {
    @GET("athlete/activities")
    suspend fun getActivities(
        @Header("Authorization") auth: String
    ) : Response<List<ActivitySummaryResponse>>
}