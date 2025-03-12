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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vee.musicapp.data.models.Movie


@Composable
fun VerticalMovieList(movies: List<Movie>, focusId: String) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Spacing between cards
        contentPadding = PaddingValues(horizontal = 16.dp) // Padding at start and end
    ) {
        items(movies) { movie ->
            MovieCardV(movie) {
            }
        }
    }
}

@Composable
fun MovieCardV(movie: Movie, onFocusChange: (Boolean) -> Unit) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(200.dp)
            .focusable()
            .focusTarget()
            .onFocusChanged { focusState ->
                onFocusChange(focusState.isFocused)
                Log.d("MovieCardV", "${movie.id}isFocused${focusState.isFocused}")
            },
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(.66f),
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

                )

            }
        }
    }
}
