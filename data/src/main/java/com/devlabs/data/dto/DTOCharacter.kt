package com.devlabs.data.dto

import com.google.gson.annotations.SerializedName

data class DTOCharacter(
    @SerializedName("_id")
    val id: String?,
    val name: String?
)