package com.example.guava.oauth.access

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccessTokenDao {
    @Query("SELECT * FROM accesstokenentity")
    suspend fun getToken(): AccessTokenEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccessToken(accessToken: AccessTokenEntity)
}