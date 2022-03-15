package com.devlabs.domain.entity

data class Movie(
    val id: String,
    val name: String,
    val duration: Int,
    val score: Float,
    var isFavorite: Boolean
)