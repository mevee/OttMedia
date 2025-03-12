package com.vee.musicapp.modules.home

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie
import kotlinx.coroutines.launch

@Composable
fun HorizontalPageView(pageData: Category) {
    val images = pageData.movies
    HorizontalPageList(pages = images)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPageList(pages: List<Movie>) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // The pager itself with peek effect
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            pageSpacing = 8.dp,  // Space between pages
            // This creates the peek effect
        ) { pageIndex ->
            // Page content
            PagerItem(pages[pageIndex])

        }
        Spacer(modifier = Modifier.height(8.dp))
        // Page indicator dots
        PageIndicator(
            pagerState = pagerState,
            pageCount = pages.size,
            onDotClick = { page ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(page)
                }
            }
        )

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(
    pagerState: PagerState,
    pageCount: Int,
    onDotClick: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(pageCount) { page ->
            val isSelected = page == pagerState.currentPage
            val dotSize by animateDpAsState(
                targetValue = if (isSelected) 12.dp else 8.dp,
                label = "DotSize"
            )

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color(0xFF3498DB) else Color.LightGray
                    )
                    .clickable { onDotClick(page) }
            )
        }
    }
}


@Composable
private fun PagerItem(movie: Movie) {
    Box(modifier = Modifier.fillMaxSize().focusable().onFocusEvent { focusState ->
        when {
            focusState.isFocused ->
                println("I'm focused!")
            focusState.hasFocus ->
                println("A child of mine has focus!")
            focusState.isCaptured ->
                println("A child of mine has focus!")
        }
    }) {
        // Background Image
        AsyncImage(
            model = movie.url,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 100.dp)
                .align(alignment = AbsoluteAlignment.CenterRight)
                .fillMaxSize()
        )
        // Black Blurred Overlay
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(500.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color.Black, Color.Black,Color.Black.copy(alpha = 0.01f))
                    )
                )
                .blur(10.dp)
        )

        // Text on Left
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(400.dp)
                .padding(16.dp)
                .align(Alignment.CenterStart)
        ) {
            Text(text = movie.name, fontSize = 20.sp, color = Color.White)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = movie.title+"", fontSize = 18.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { Log.d("Button","Play now Clicked")
            }) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Play Now", fontSize = 18.sp, color = Color.Blue)
            }
        }
    }
}
