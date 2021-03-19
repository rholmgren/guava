package com.example.guava.oauth.refresh

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RefreshTokenEntity(
    @PrimaryKey
    @ColumnInfo(name = "athleteID")
    val athleteID: Int,
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "scope")
    val scope: Boolean
)