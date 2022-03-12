package com.devlabs.data.mapper

import com.devlabs.data.dto.DTOMovie
import com.devlabs.data.dto.DTOMoviesResponse
import com.devlabs.domain.entity.Movie

class MoviesRemoteMapper : BaseMapper<DTOMoviesResponse?, List<Movie>>() {
    override fun transform(entity: DTOMoviesResponse?): List<Movie> {
        val moviesList = arrayListOf<Movie>()
        entity?.moviesList?.map {
            moviesList.add(
                Movie(it.id ?: "", it.name ?: "", it.duration ?: 0, it.score ?: 0f)
            )
        }
        return moviesList
    }
}