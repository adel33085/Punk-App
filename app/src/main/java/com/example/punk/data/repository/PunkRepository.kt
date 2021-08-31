package com.example.punk.data.repository

import com.example.punk.base.platform.BaseRepository
import com.example.punk.base.platform.Result
import com.example.punk.data.api.PunkApi
import com.example.punk.domain.Beer
import com.example.punk.domain.BeerDetails
import com.example.punk.entity.toBeer
import com.example.punk.entity.toBeerDetails
import javax.inject.Inject

class PunkRepository @Inject constructor(
    private val punkApi: PunkApi
) : BaseRepository(), IPunkRepository {

    override suspend fun getBeers(pageNumber: Int, pageSize: Int): Result<List<Beer>> {
        val result = safeApiCall {
            punkApi.getBeers(pageNumber, pageSize)
        }
        return when (result) {
            is Result.Success -> {
                Result.Success(result.data.mapNotNull { it.toBeer() })
            }
            is Result.Error -> {
                result
            }
        }
    }

    override suspend fun getBeerDetails(beerId: Int): Result<BeerDetails> {
        val result = safeApiCall {
            punkApi.getBeerDetails(beerId)
        }
        return when (result) {
            is Result.Success -> {
                Result.Success(result.data.get(0).toBeerDetails()!!)
            }
            is Result.Error -> {
                result
            }
        }
    }
}
