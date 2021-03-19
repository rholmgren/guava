package com.example.guava.workout

interface WorkoutRepository {
    fun getAllActivities() : List<Activity>
}