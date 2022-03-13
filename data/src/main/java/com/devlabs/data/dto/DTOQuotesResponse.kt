package com.devlabs.data.dto

import com.google.gson.annotations.SerializedName

data class DTOQuotesResponse(
    @SerializedName("docs")
    val quotesList: List<DTOQuote>
)