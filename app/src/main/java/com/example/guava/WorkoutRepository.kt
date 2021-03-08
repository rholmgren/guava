package com.example.guava

interface WorkoutRepository {
    fun getAllActivities() : List<Activity>
}