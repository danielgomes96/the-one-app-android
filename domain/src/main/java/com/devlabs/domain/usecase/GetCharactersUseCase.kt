package com.devlabs.domain.usecase

import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : GetCharactersUseCase {
    override suspend fun execute(currentPage: Int): Flow<ResultWrapper<List<Character>>> =
        charactersRepository.getCharacters(currentPage)
}

interface GetCharactersUseCase {
    suspend fun execute(currentPage: Int): Flow<ResultWrapper<List<Character>>>
}