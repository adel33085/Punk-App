package com.example.punk.usecase

import com.example.punk.base.platform.Result
import com.example.punk.data.repository.IPunkRepository
import com.example.punk.domain.BeerDetails
import javax.inject.Inject

class GetBeerDetailsUseCase @Inject constructor(private val repository: IPunkRepository) {

    suspend operator fun invoke(beerId: Int): Result<BeerDetails> {
        return repository.getBeerDetails(beerId)
    }
}
