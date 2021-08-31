package com.example.punk.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.punk.base.ui.ErrorScreen
import com.example.punk.base.ui.LoadingScreen

@Composable
fun BeerDetailsActivityScreen(viewModel: BeerDetailsViewModel) {
    when (val viewState = viewModel.viewState.observeAsState().value) {
        is ViewState.Loading -> {
            LoadingScreen()
        }
        is ViewState.Success -> {
            BeerDetailsScreen(beerDetails = viewState.beerDetails)
        }
        is ViewState.Error -> {
            ErrorScreen(
                errorMessage = viewState.errorMessage,
                retry = { viewModel.getBeerDetails() }
            )
        }
    }
}
