package com.example.punk.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.punk.base.platform.BaseViewModel
import com.example.punk.base.platform.Result
import com.example.punk.domain.BeerDetails
import com.example.punk.usecase.GetBeerDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class BeerDetailsViewModel @AssistedInject constructor(
    @Assisted private val beerId: Int,
    private val getBeerDetailsUseCase: GetBeerDetailsUseCase
) : BaseViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    init {
        getBeerDetails()
    }

    fun getBeerDetails() {
        _viewState.value = ViewState.Loading
        wrapBlockingOperation {
            when (val result = getBeerDetailsUseCase.invoke(beerId)) {
                is Result.Success -> {
                    _viewState.value = ViewState.Success(result.data)
                }
                is Result.Error -> {
                    _viewState.value = ViewState.Error(result.exception.errorMessage!!)
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(beerId: Int): BeerDetailsViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            beerId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(beerId) as T
            }
        }
    }
}

sealed class ViewState {

    object Loading : ViewState()

    data class Success(val beerDetails: BeerDetails) : ViewState()

    data class Error(val errorMessage: String) : ViewState()
}
