package com.example.punk.usecase

import com.example.punk.base.platform.Result
import com.example.punk.data.repository.IPunkRepository
import com.example.punk.domain.Beer
import javax.inject.Inject

class GetBeersUseCase @Inject constructor(private val repository: IPunkRepository) {

    suspend operator fun invoke(pageNumber: Int, pageSize: Int): Result<List<Beer>> {
        return repository.getBeers(pageNumber, pageSize)
    }
}
