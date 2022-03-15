package com.devlabs.data.mapper

import com.devlabs.data.dto.DTOMovie
import com.devlabs.data.dto.DTOMoviesResponse
import com.devlabs.domain.entity.Movie
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MoviesMapperTest {
    private val movieMapper by lazy {
        MoviesLocalMapper()
    }
    companion object {
        private val MOVIES_DTO_LIST = listOf<DTOMovie>(
            DTOMovie(id = "5cd95395de30eff6ebccde56", name = "The Lord of the Rings Series", duration = 558, score = 94f)
        )
        val FAKE_DTO_FILM = DTOMoviesResponse(MOVIES_DTO_LIST)
        val FAKE_FILM = Movie(id = "5cd95395de30eff6ebccde56", name = "The Lord of the Rings Series", duration = 558, score = 94f, isFavorite = false)
    }

    @Test
    fun `GIVEN a fake response WHEN transforming it to entity THEN get proper movie object`() {
        // Given
        // When
        val transformedMovie = movieMapper.transform(FAKE_DTO_FILM)

        // Then
        assertEquals(FAKE_FILM.id, transformedMovie[0].id)
        assertEquals(FAKE_FILM.name, transformedMovie[0].name)
    }

    @Test
    fun `GIVEN a null fake response WHEN transforming it to entity THEN get empty movies list`() {
        // Given
        // When
        val transformedMovie = movieMapper.transform(null as DTOMoviesResponse?)

        // Then
        assert(transformedMovie.isEmpty())
    }
}