package com.example.guava.oauth.access

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccessTokenEntity(
    @PrimaryKey
    @ColumnInfo(name = "athleteID")
    val athleteID: Int,
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "scope")
    val scope: Boolean,
    @ColumnInfo(name = "expires_in")
    val expiresInSecs: Int
)