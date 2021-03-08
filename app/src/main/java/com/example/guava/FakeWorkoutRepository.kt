package com.example.guava

import javax.inject.Inject

class FakeWorkoutRepository @Inject constructor(): WorkoutRepository {
    override fun getAllActivities(): List<Activity> {
        return listOf(
            Activity(
                owner = "Rachelle Holmgren",
                activityTime = "September 9, 2020 at 8:04 AM",
                title = "Morning Run",
                distance = "1.06 mi",
                pace = "8:29/mi",
                totalLength = "9m 0s"
            ),
            Activity(
                owner = "Rahul Rajeev",
                activityTime = "September 11, 2020 at 6:45 AM",
                title = "Bernal Heights Jog",
                distance = "4.62 mi",
                pace = "10.41/mi",
                totalLength = "53m 20s"
            )

        )
    }
}

data class Activity(
    val owner: String,
    val activityTime: String,
    val title: String,
    val distance: String,
    val pace: String,
    val totalLength: String
)