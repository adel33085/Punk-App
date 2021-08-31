package com.example.punk.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.punk.base.platform.Result
import com.example.punk.domain.Beer
import com.example.punk.usecase.GetBeersUseCase

class BeersDataSource(
    private val getBeersUseCase: GetBeersUseCase,
    private val pageSize: Int
) : PagingSource<Int, Beer>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        val pageNumber = params.key ?: 1
        return when (val result = getBeersUseCase.invoke(pageNumber, pageSize)) {
            is Result.Success -> {
                LoadResult.Page(
                    data = result.data,
                    prevKey = if (pageNumber == 1) null else pageNumber - 1,
                    nextKey = if (result.data.isEmpty()) null else pageNumber + 1
                )
            }
            is Result.Error -> {
                LoadResult.Error(Exception(result.exception.errorMessage))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        TODO("Not yet implemented")
    }
}
