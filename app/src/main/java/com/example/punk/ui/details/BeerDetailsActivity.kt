package com.example.punk.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.punk.theme.PunkTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BeerDetailsActivity : ComponentActivity() {

    @Inject
    lateinit var factory: BeerDetailsViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val beerId = intent.getIntExtra("beer_id", -101)
        val viewModel: BeerDetailsViewModel by viewModels {
            BeerDetailsViewModel.provideFactory(factory, beerId)
        }
        setContent {
            PunkTheme(darkTheme = false) {
                BeerDetailsActivityScreen(viewModel = viewModel)
            }
        }
    }
}
