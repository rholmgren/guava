package com.example.guava.workout

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkoutFeedModule {
    @Binds
    abstract fun bindWorkoutRepository(workoutRepositoryImpl: RealWorkoutFeedRepository): WorkoutRepository
}