package com.example.guava.di

import com.example.guava.WorkoutRepository
import com.example.guava.FakeWorkoutRepository
import com.example.guava.oauth.OAuthService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {
    @Binds
    abstract fun bindActivityRepository(activityRepositoryImpl: FakeWorkoutRepository): WorkoutRepository
}

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideMoshi(adapterFactorySet: Set<@JvmSuppressWildcards JsonAdapter.Factory>): Moshi {
        val builder = Moshi.Builder()

        adapterFactorySet.forEach {
            builder.add(it)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideStravaAuthService(okHttpClient: OkHttpClient): OAuthService {
        return Retrofit.Builder()
            .baseUrl("https://www.strava.com/api/v3/oauth/token/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(OAuthService::class.java)

    }


}