package com.devlabs.data.dto

import com.google.gson.annotations.SerializedName

data class DTOMovie(
    @SerializedName("_id")
    val id: String?,
    val name: String?,
    @SerializedName("runtimeInMinutes")
    val duration: Int?,
    @SerializedName("rottenTomatoesScore")
    val score: Float?
)