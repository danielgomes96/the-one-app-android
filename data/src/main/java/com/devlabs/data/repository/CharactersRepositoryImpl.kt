package com.devlabs.data.repository

import com.devlabs.data.dto.DTOCharactersResponse
import com.devlabs.data.dto.DTOQuotesResponse
import com.devlabs.data.mapper.CharactersRemoteMapper
import com.devlabs.data.mapper.QuotesRemoteMapper
import com.devlabs.data.service.TheOneAPI
import com.devlabs.domain.entity.Character
import com.devlabs.domain.entity.Quote
import com.devlabs.domain.entity.ResultWrapper
import com.devlabs.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersRepositoryImpl(
    private val theOneAPI: TheOneAPI
) : CharactersRepository {

    companion object {
        const val PAGE_SIZE = 40
    }

    override suspend fun getCharacters(currentPage: Int): Flow<ResultWrapper<List<Character>>> = flow {
        emit(ResultWrapper.Loading)
        runCatching {
            theOneAPI.getCharacters(currentPage, PAGE_SIZE)
        }.onSuccess {
            if (it.body()?.charactersList?.isNullOrEmpty() == true) {
                emit(ResultWrapper.Empty)
            } else {
                emit(
                    ResultWrapper.Success(
                        CharactersRemoteMapper().transform(
                            it.body() ?: DTOCharactersResponse(
                                emptyList()
                            )
                        )
                    )
                )
            }
        }.onFailure {
            emit(ResultWrapper.GenericError(null, it.localizedMessage))
        }
    }

    override suspend fun getQuotes(characterId: String): Flow<ResultWrapper<List<Quote>>> = flow {
        emit(ResultWrapper.Loading)
        runCatching {
            theOneAPI.getCharacterQuotes(characterId)
        }.onSuccess {
            if (it.body()?.quotesList?.isNullOrEmpty() == true) {
                emit(ResultWrapper.Empty)
            } else {
                emit(
                    ResultWrapper.Success(
                        QuotesRemoteMapper().transform(
                            it.body() ?: DTOQuotesResponse(
                                emptyList()
                            )
                        )
                    )
                )
            }
        }.onFailure {
            emit(ResultWrapper.GenericError(null, it.localizedMessage))
        }
    }
}