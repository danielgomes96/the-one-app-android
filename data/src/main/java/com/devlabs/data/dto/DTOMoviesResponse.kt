package com.devlabs.data.dto

import com.google.gson.annotations.SerializedName

data class DTOMoviesResponse(
    @SerializedName("docs")
    val moviesList: List<DTOMovie>
)