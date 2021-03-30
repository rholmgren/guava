package com.example.guava.workout

import com.example.guava.oauth.access.AccessTokenDao
import javax.inject.Inject

class RealWorkoutFeedRepository @Inject constructor(
    private val workoutService: WorkoutService,
    private val accessTokenDao: AccessTokenDao
): WorkoutRepository {
    override suspend fun getAllActivities(): List<Activity> {
        val accessToken = accessTokenDao.getToken()!!.code
        val activitiesResponse = workoutService.getActivities(auth = accessToken)

        return if (activitiesResponse.isSuccessful) {
            activitiesResponse.body()!!.map { activitySummaryResponse ->
                Activity(
                    owner = "Rachelle Holmgren",
                    activityTime = "3/20/2020 5:01 PM",
                    title = activitySummaryResponse.name,
                    distance = activitySummaryResponse.distanceInMeters.toString(),
                    pace = "8:30/mi",
                    totalLength = activitySummaryResponse.elapsedTimeInSecs.toString()
                )
            }
        } else {
            emptyList()
        }
    }
}