package com.devlabs.data.mapper

import com.devlabs.domain.entity.Movie

class MoviesRemoteMapper : BaseMapper<List<com.devlabs.data.local.Movie>?, List<Movie>>() {
    override fun transform(entity: List<com.devlabs.data.local.Movie>?): List<Movie> {
        val moviesList = arrayListOf<Movie>()
        entity?.map {
            moviesList.add(
                Movie(it.id, it.name, it.duration, it.score, false)
            )
        }
        return moviesList
    }
}