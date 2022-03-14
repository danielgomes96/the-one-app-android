package com.devlabs.data.mapper

import com.devlabs.data.dto.DTOMoviesResponse
import com.devlabs.data.local.Movie

class MoviesLocalMapper : BaseMapper<DTOMoviesResponse?, List<Movie>>() {
    override fun transform(entity: DTOMoviesResponse?): List<Movie> {
        val moviesList = arrayListOf<Movie>()
        entity?.moviesList?.map {
            moviesList.add(
                Movie(it.id ?: "", it.name ?: "", it.duration ?: 0, it.score ?: 0f, isFavorite = false)
            )
        }
        return moviesList
    }
}