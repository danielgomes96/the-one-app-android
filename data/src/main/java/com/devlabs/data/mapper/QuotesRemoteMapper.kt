package com.devlabs.data.mapper

import com.devlabs.data.dto.DTOQuotesResponse
import com.devlabs.domain.entity.Quote

class QuotesRemoteMapper : BaseMapper<DTOQuotesResponse?, List<Quote>>() {
    override fun transform(entity: DTOQuotesResponse?): List<Quote> {
        val moviesList = arrayListOf<Quote>()
        entity?.quotesList?.map {
            moviesList.add(
                Quote(it.id ?: "", it.dialog ?: "No dialog")
            )
        }
        return moviesList
    }
}