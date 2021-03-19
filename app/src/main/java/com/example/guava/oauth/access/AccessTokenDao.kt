package com.example.guava.oauth.access

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AccessTokenDao {
    @Query("SELECT * FROM accesstokenentity WHERE athleteID = :athleteId")
    suspend fun getToken(athleteId: Int): AccessTokenEntity?
}