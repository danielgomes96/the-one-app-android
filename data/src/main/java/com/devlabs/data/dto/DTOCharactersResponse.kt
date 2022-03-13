package com.devlabs.data.dto

import com.google.gson.annotations.SerializedName

data class DTOCharactersResponse(
    @SerializedName("docs")
    val charactersList: List<DTOCharacter>
)