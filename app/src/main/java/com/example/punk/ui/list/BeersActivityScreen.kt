package com.example.punk.ui.list

import androidx.compose.runtime.Composable

@Composable
fun BeersActivityScreen(viewModel: BeersViewModel) {
    BeersScreen(beers = viewModel.beers)
}
