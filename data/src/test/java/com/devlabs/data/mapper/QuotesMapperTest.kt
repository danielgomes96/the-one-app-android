package com.devlabs.data.mapper

import com.devlabs.data.dto.DTOQuote
import com.devlabs.data.dto.DTOQuotesResponse
import com.devlabs.domain.entity.Quote
import junit.framework.TestCase
import org.junit.Test

class QuotesMapperTest {
    private val quotesMapper by lazy {
        QuotesRemoteMapper()
    }
    companion object {
        private val QUOTES_DTO_LIST = listOf<DTOQuote>(
            DTOQuote(id = "5cd96e05de30eff6ebcce7f6", dialog = "Gandalf?")
        )
        val FAKE_DTO_QUOTE = DTOQuotesResponse(QUOTES_DTO_LIST)
        val FAKE_QUOTE = Quote(id = "5cd96e05de30eff6ebcce7f6", dialog = "Gandalf?")
    }

    @Test
    fun `GIVEN a fake response WHEN transforming it to entity THEN get proper quotes object`() {
        // Given
        // When
        val transformedQuote = quotesMapper.transform(FAKE_DTO_QUOTE)

        // Then
        TestCase.assertEquals(FAKE_QUOTE.id, transformedQuote[0].id)
        TestCase.assertEquals(FAKE_QUOTE.dialog, transformedQuote[0].dialog)
    }

    @Test
    fun `GIVEN a null fake response WHEN transforming it to entity THEN get empty quotes list`() {
        // Given
        // When
        val transformedQuote = quotesMapper.transform(null as DTOQuotesResponse?)

        // Then
        assert(transformedQuote.isEmpty())
    }
}