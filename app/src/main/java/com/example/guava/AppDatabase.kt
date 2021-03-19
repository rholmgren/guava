package com.example.guava

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.guava.oauth.access.AccessTokenDao
import com.example.guava.oauth.access.AccessTokenEntity
import com.example.guava.oauth.refresh.RefreshTokenEntity
import com.example.guava.oauth.refresh.RefreshTokenDao

@Database(entities = arrayOf(AccessTokenEntity::class, RefreshTokenEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): AccessTokenDao
    abstract fun refreshDao(): RefreshTokenDao
}