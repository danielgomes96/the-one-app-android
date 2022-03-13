package com.devlabs.data.dto

import com.google.gson.annotations.SerializedName

data class DTOQuote(
    @SerializedName("_id")
    val id: String?,
    val dialog: String?
)