package com.vee.musicapp.modules.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import coil3.compose.AsyncImage
import com.vee.musicapp.R
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie

@Composable
fun HorizontalPageView(pageData: Category) {
    val images = pageData.movies
    HorizontalPageList(pages = images)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalTvMaterial3Api::class)
@Composable
fun HorizontalPageList(pages: List<Movie>) {
    Carousel(
        itemCount = pages.size,
        modifier = Modifier.fillMaxSize(),
    ) { indexOfCarouselItem ->
        val featuredMovie = pages[indexOfCarouselItem]
        val backgroundColor = MaterialTheme.colorScheme.background
        Box {
            AsyncImage(
                model = featuredMovie.url,
                contentDescription = "null",
                placeholder = painterResource(
                    id = R.drawable.ic_launcher_background
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Box(contentAlignment = Alignment.BottomStart,
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        val brush = Brush.horizontalGradient(
                            listOf(
                                Color.Black,
                                Color.Black.copy(alpha = .5f),
                                backgroundColor.copy(alpha = .5f),
                            )
                        )
                        drawRect(brush)
                    }) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .width(300.dp)
                ) {
                    Text(
                        text = featuredMovie.name, style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = featuredMovie.subTitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    androidx.tv.material3.Button(onClick = { /*onMovieSelected(featuredMovie)*/ }) {
                        androidx.tv.material3.Text(
                            text = "Show Details",
                            style = androidx.compose.material3.MaterialTheme.typography.titleSmall,
                        )
                    }
                }
            }
        }
    }
}

