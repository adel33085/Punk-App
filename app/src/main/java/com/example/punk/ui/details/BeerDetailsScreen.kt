package com.example.punk.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.punk.R
import com.example.punk.domain.BeerDetails
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BeerDetailsScreen(beerDetails: BeerDetails) {
    val scrollState = rememberScrollState()
    var foodPairing = ""
    beerDetails.foodPairing.forEach {
        foodPairing += "- ${it}\n"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(scrollState)
    ) {
        GlideImage(
            imageModel = beerDetails.imageUrl,
            contentScale = ContentScale.Fit,
            circularReveal = CircularReveal(),
            error = painterResource(id = R.drawable.ic_baseline_broken_image_24),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "${beerDetails.name} (${beerDetails.tagline})",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Text(
            text = beerDetails.description,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Food Paring",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Text(
            text = foodPairing.trim(),
            fontSize = 14.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
