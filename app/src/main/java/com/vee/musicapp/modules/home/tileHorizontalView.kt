package com.vee.musicapp.modules.home

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.util.AppConstants
import com.vee.musicapp.viewmodel.MovieViewModel


@Composable
fun HorizontalMovieList(movies: List<Movie>,onClick: ((Movie) -> Unit)?=null) {
    Log.d("HorizontalMovieList", "movies: $movies")
    val configuration = LocalConfiguration.current
//    val bringIntoViewRequester = remember { BringIntoViewRequester() }
      LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
//          horizontalArrangement = Arrangement.Start,
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Spacing between cards
        contentPadding = PaddingValues(start = 16.dp, end = (configuration.screenWidthDp).dp), // Padding at start and end
    ) {
        itemsIndexed(movies,key = {_, item ->  item.id}) { index,movie ->
            MovieCardH(index,movie, onClick = {
                println("MovieCardH clicked:${movie.name}")
                onClick?.invoke(movie)
            })
        }
    }
}

@Composable
fun MovieCardH(index: Int, movie: Movie, onClick: () -> Unit = {}) {
    var hasFocus by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .height(if (hasFocus) 106.dp else 100.dp)
            .border(
                width = if (hasFocus) 2.dp else 0.dp,
                shape = RoundedCornerShape(5.dp),
                color = if (hasFocus) Color.White else Color.Transparent
            )
            .aspectRatio(2.0f)
             .onFocusChanged {
                if(it.hasFocus){
                }
                hasFocus = it.hasFocus
            },
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = onClick
    ) {
        AsyncImage(
            model = if (movie.url.isNullOrEmpty()) AppConstants.noUrlLink else movie.url,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(if (hasFocus) 3.5.dp else 0.dp)
                .clip(RoundedCornerShape(4.dp))
        )
//            if (movie.url.isNullOrEmpty()) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(color = Color.Black.copy(alpha = 0.7f))
//                )
//                Text(
//                    text = movie.name, textAlign = TextAlign.Center, style = TextStyle(
//                        textAlign = TextAlign.Center,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    ), modifier = Modifier.padding(6.dp)
//                )
//            }
    }
}
