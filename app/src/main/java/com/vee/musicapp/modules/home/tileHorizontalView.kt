package com.vee.musicapp.modules.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.ExperimentalTvMaterial3Api
import coil3.compose.AsyncImage
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.viewmodel.MovieViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
//@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HorizontalMovieList(movies: List<Movie>, focusId: String, viewModel: MovieViewModel) {
//    var hasFocus by remember { mutableStateOf(false) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    ProvideLazyListPivotOffset() {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp), // Spacing between cards
            contentPadding = PaddingValues(horizontal = 16.dp), // Padding at start and end
//        pivotOffsets = PivotOffsets(0.3f)
        ) {
            items(movies) { movie ->
                MovieCardH(movie, onClick = {
                    println("MovieCardH clicked:${movie.name}")
                    viewModel.currentShow.value = movie
//                    onItemSelected(movie)
                })
            }
        }
    }
}

@Composable
fun MovieCardH(movie: Movie, onClick: () -> Unit = {}) {
    var hasFocus by remember { mutableStateOf(false) }
      Card(
        modifier = Modifier
            .height(if(hasFocus) 116.dp else 100.dp)
            .border(width = if(hasFocus)6.dp else 0.dp,
                shape = RoundedCornerShape(8.dp),
                color = if(hasFocus) Color.White else Color.Transparent)
            .aspectRatio(2.0f)
            .onFocusChanged {
                hasFocus =it.hasFocus
            },
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = movie.url,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()

            )
        }
    }
}
