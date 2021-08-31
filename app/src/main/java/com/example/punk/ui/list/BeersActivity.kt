package com.example.punk.ui.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.punk.theme.PunkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeersActivity : ComponentActivity() {

    private val viewModel: BeersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PunkTheme(darkTheme = false) {
                BeersActivityScreen(viewModel = viewModel)
            }
        }
    }
}
