package com.example.punk.ui.list

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.punk.R
import com.example.punk.base.ui.ErrorItem
import com.example.punk.base.ui.ErrorScreen
import com.example.punk.base.ui.LoadingItem
import com.example.punk.base.ui.LoadingScreen
import com.example.punk.domain.Beer
import com.example.punk.ui.details.BeerDetailsActivity
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow

@Composable
fun BeersScreen(beers: Flow<PagingData<Beer>>) {
    val lazyBeerItems = beers.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.background(Color.White)) {
        items(lazyBeerItems) {
            BeerListItem(beer = it!!)
        }

        lazyBeerItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingScreen()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        LoadingItem()
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val error = lazyBeerItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorScreen(
                            errorMessage = error.error.message ?: "something went wrong",
                            retry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val error = lazyBeerItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            errorMessage = error.error.message ?: "something went wrong",
                            retry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BeerListItem(beer: Beer) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        val context = LocalContext.current
        val (beerImg, beerNameTxt, beerTaglineTxt, showDetailsBtn) = createRefs()

        GlideImage(
            imageModel = beer.imageUrl,
            contentScale = ContentScale.Fit,
            circularReveal = CircularReveal(),
            error = painterResource(id = R.drawable.ic_baseline_broken_image_24),
            modifier = Modifier
                .size(100.dp)
                .constrainAs(beerImg) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = beer.name,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(beerNameTxt) {
                top.linkTo(beerImg.top)
                linkTo(
                    start = beerImg.end,
                    end = parent.end,
                    startMargin = 4.dp,
                    endMargin = 0.dp
                )
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = beer.tagline,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(beerTaglineTxt) {
                top.linkTo(beerNameTxt.bottom)
                linkTo(
                    start = beerNameTxt.start,
                    end = beerNameTxt.end
                )
                width = Dimension.fillToConstraints
            }
        )

        Button(
            onClick = {
                val intent = Intent(context, BeerDetailsActivity::class.java)
                intent.putExtra("beer_id", beer.id)
                context.startActivity(intent)
            },
            modifier = Modifier.constrainAs(showDetailsBtn) {
                top.linkTo(beerTaglineTxt.bottom, margin = 8.dp)
                start.linkTo(beerTaglineTxt.start)
            }
        ) {
            Text(text = "Show Details")
        }
    }
}
