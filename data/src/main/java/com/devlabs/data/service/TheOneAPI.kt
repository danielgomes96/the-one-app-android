package com.devlabs.data.service

import com.devlabs.data.dto.DTOCharactersResponse
import com.devlabs.data.dto.DTOMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheOneAPI {
    @GET("movie/")
    suspend fun getMovies(): Response<DTOMoviesResponse>

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<DTOCharactersResponse>
}