package com.devlabs.data.service

import com.devlabs.data.dto.DTOMoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface TheOneAPI {
    @GET("movie/")
    suspend fun getMovies(): Response<DTOMoviesResponse>
}