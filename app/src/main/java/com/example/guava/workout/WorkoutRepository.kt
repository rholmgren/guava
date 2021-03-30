package com.example.guava.workout

interface WorkoutRepository {
    suspend fun getAllActivities() : List<Activity>
}