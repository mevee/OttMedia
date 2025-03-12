package com.vee.musicapp.modules.home

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.viewmodel.MovieViewModel


@Composable
fun HorizontalMovieList(movies: List<Movie>, focusId: String, viewModel: MovieViewModel) {
//    var hasFocus by remember { mutableStateOf(false) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp), // Spacing between cards
        contentPadding = PaddingValues(horizontal = 16.dp) // Padding at start and end
    ) {
        items(movies) { movie ->
            MovieCardH(movie, focusId) { hasFocus ->
                Log.d("MovieCardH", "${movie.id}hasFocus$hasFocus")
                viewModel.updateFocus(movie.id)
            }
        }
    }
}

@Composable
fun MovieCardH(movie: Movie, focusId: String, onFocusChange: (Boolean) -> Unit) {
    var hasFocus by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Card(modifier = Modifier
        .height(100.dp)
        .height(if (focusId == movie.id) 180.dp else 100.dp)
        .aspectRatio(2.0f)
        .focusable()
        .focusRequester(focusRequester)
        .onFocusChanged { focusState ->
            hasFocus = focusState.isFocused
            onFocusChange(focusState.isFocused)
        },
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = movie.url,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .focusable()
                    .focusTarget()
                    .onFocusChanged { focusState ->
                        onFocusChange(focusState.isFocused)
                        Log.d("MovieCardH", "${movie.id}isFocused$hasFocus")
                    }

            )
        }
    }
}
