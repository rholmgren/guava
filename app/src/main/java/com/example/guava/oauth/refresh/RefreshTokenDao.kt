package com.example.guava.oauth.refresh

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RefreshTokenDao {
    @Query("SELECT * FROM refreshtokenentity")
    suspend fun getToken(): RefreshTokenEntity?
}