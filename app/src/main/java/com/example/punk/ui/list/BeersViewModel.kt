package com.example.punk.ui.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.punk.base.platform.BaseViewModel
import com.example.punk.domain.Beer
import com.example.punk.paging.BeersDataSource
import com.example.punk.usecase.GetBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class BeersViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase
) : BaseViewModel() {

    val beers: Flow<PagingData<Beer>> = Pager(PagingConfig(pageSize = 20)) {
        BeersDataSource(getBeersUseCase, 25)
    }.flow
}
