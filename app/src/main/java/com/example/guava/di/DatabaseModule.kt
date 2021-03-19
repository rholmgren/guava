package com.example.guava.di

import android.content.Context
import androidx.room.Room
import com.example.guava.AppDatabase
import com.example.guava.oauth.access.AccessTokenDao
import com.example.guava.oauth.refresh.RefreshTokenDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideTokenDao(appDatabase: AppDatabase): AccessTokenDao {
        return appDatabase.tokenDao()
    }

    @Provides
    fun provideRefreshDao(appDatabase: AppDatabase): RefreshTokenDao {
        return appDatabase.refreshDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "token_database"
        ).build()
    }
}