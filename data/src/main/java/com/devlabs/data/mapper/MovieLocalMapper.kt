package com.devlabs.data.mapper

import com.devlabs.data.local.Movie

class MovieLocalMapper : BaseMapper<com.devlabs.domain.entity.Movie, Movie>() {
    override fun transform(entity: com.devlabs.domain.entity.Movie): Movie {
        return Movie(
            entity.id,
            entity.name,
            entity.duration,
            entity.score,
            entity.isFavorite
        )
    }
}