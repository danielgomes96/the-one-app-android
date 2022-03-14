package com.devlabs.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: String,
    val name: String,
    val duration: Int,
    val score: Float,
    val isFavorite: Boolean
)