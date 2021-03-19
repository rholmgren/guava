package com.example.guava.oauth.access

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AccessTokenDao {
    @Query("SELECT * FROM accesstokenentity")
    suspend fun getToken(): AccessTokenEntity?
}