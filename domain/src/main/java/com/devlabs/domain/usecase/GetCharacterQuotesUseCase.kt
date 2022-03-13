package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Movie
import com.devlabs.domain.entity.Quote
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.CharactersRepository
import com.devlabs.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterQuotesUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : GetCharacterQuotesUseCase {
    override suspend fun execute(characterId: String): Flow<ResultWrapper<List<Quote>>> =
        charactersRepository.getQuotes(characterId)
}

interface GetCharacterQuotesUseCase {
    suspend fun execute(characterId: String): Flow<ResultWrapper<List<Quote>>>
}