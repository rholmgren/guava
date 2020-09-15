package com.example.guava.di

import com.example.guava.ActivityRepository
import com.example.guava.FakeActivityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {
    @Binds
    abstract fun bindActivityRepository(activityRepositoryImpl: FakeActivityRepository): ActivityRepository
}