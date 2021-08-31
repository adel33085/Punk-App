package com.example.punk.data.repository

import com.example.punk.base.platform.Result
import com.example.punk.domain.Beer
import com.example.punk.domain.BeerDetails

interface IPunkRepository {

    suspend fun getBeers(pageNumber: Int, pageSize: Int): Result<List<Beer>>

    suspend fun getBeerDetails(beerId: Int): Result<BeerDetails>
}
