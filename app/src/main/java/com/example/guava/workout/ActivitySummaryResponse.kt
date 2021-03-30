package com.example.guava.workout

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ActivitySummaryResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "distance")
    val distanceInMeters: Float,
    @Json(name = "elapsed_time")
    val elapsedTimeInSecs: Int,
    @Json(name = "total_elevation_gain")
    val totalElevationGain: Float,
)