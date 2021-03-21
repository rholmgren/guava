package com.example.guava.oauth.refresh

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RefreshTokenDao {
    @Query("SELECT * FROM refreshtokenentity")
    suspend fun getToken(): RefreshTokenEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRefreshToken(refreshToken: RefreshTokenEntity)
}