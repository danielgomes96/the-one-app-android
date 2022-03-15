package com.devlabs.data.repository

import com.devlabs.data.dto.DTOQuote
import com.devlabs.data.dto.DTOQuotesResponse
import com.devlabs.data.service.TheOneAPI
import com.devlabs.domain.entity.Quote
import com.devlabs.domain.entity.ResultWrapper
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class CharactersRepositoryTest {

    @MockK
    private val theOneAPI: TheOneAPI = mockk()

    private val characterRepository = spyk(CharactersRepositoryImpl(theOneAPI))

    companion object {
        private const val FAKE_CHARACTER_ID = "5cd99d4bde30eff6ebccfc15"
        private val FAKE_LIST_QUOTES = listOf(
            Quote(
                id = "Yoda",
                dialog = "896BBY"
            )
        )
        private val FAKE_DTO_LIST_CHARACTER = listOf(
            DTOQuote(
                id = "Yoda",
                dialog = "896BBY"
            )
        )

        val LOADING_RESPONSE = ResultWrapper.Loading
        val EMPTY_RESPONSE = ResultWrapper.Empty
        val SUCCESS_RESPONSE = ResultWrapper.Success(FAKE_LIST_QUOTES)
        val ERROR_RESPONSE = ResultWrapper.GenericError(null, null)
        val FAKE_DTO_QUOTES = DTOQuotesResponse(FAKE_DTO_LIST_CHARACTER)
        val FAKE_DTO_EMPTY_QUOTES = DTOQuotesResponse(listOf())
    }

    @Test
    fun `GIVEN a success response WHEN service is called THEN get quotes list`() = runBlocking {
        // given
        val mockResponse = Response.success(FAKE_DTO_QUOTES)

        coEvery {
            theOneAPI.getCharacterQuotes(FAKE_CHARACTER_ID)
        } returns mockResponse

        // When
        val flowResponse = characterRepository.getQuotes(
            FAKE_CHARACTER_ID
        )

        val loadingResponse = flowResponse.first()
        val successResponse = flowResponse.last()

        // Then
        loadingResponse shouldBe LOADING_RESPONSE
        successResponse shouldBe SUCCESS_RESPONSE
    }

    @Test
    fun `GIVEN an empty response WHEN service is called THEN get empty quotes list`() = runBlocking {
        // Given
        val mockResponse = Response.success(FAKE_DTO_EMPTY_QUOTES)

        coEvery {
            theOneAPI.getCharacterQuotes(FAKE_CHARACTER_ID)
        } returns mockResponse

        // When
        val flowResponse = characterRepository.getQuotes(
            FAKE_CHARACTER_ID
        ).toList()

        val loadingResponse = flowResponse.first()
        val emptyResponse = flowResponse.last()

        // Then
        loadingResponse shouldBe LOADING_RESPONSE
        emptyResponse shouldBe EMPTY_RESPONSE
    }

    @Test
    fun `GIVEN a network error response WHEN get quotes is called THEN throw an error`() = runBlocking {
        // Given
        val exceptionResponse = IOException()

        coEvery {
            theOneAPI.getCharacterQuotes(FAKE_CHARACTER_ID)
        } throws exceptionResponse

        // When
        val flowResponse = characterRepository.getQuotes(
            FAKE_CHARACTER_ID
        )

        val loadingResponse = flowResponse.first()
        val errorResponse = flowResponse.last()

        // Then
        loadingResponse shouldBe LOADING_RESPONSE
        errorResponse shouldBe ERROR_RESPONSE
    }

}