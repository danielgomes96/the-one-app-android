package com.devlabs.domain.repository

import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.Quote
import com.devlabs.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacters(currentPage: Int): Flow<ResultWrapper<List<Character>>>
    suspend fun getQuotes(characterId: String): Flow<ResultWrapper<List<Quote>>>
}