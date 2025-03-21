package com.vee.musicapp.modules.home

import android.util.Log
import android.view.ScrollFeedbackProvider
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import coil3.compose.AsyncImage
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.ui.theme.Dimens
import com.vee.musicapp.util.AppConstants

@Composable
fun HorizontalMovieList(
    movies: List<Movie>,
    onClick: ((Movie) -> Unit)? = null,
    onHovered: ((Movie) -> Unit)? = null,
) {
    val configuration = LocalConfiguration.current
    val state = rememberLazyListState()

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = state,
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp16),
        contentPadding = PaddingValues(
            start = Dimens.dp16, end = configuration.screenWidthDp.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(movies, key = { _, item -> item.id }) { _, movie ->
            MovieCardH(
                movie = movie,
                onClick = { onClick?.invoke(movie) },
                onFocus = { onHovered?.invoke(movie) }
            )
        }
    }
}

@Composable
fun MovieCardH(
    movie: Movie,
    onClick: () -> Unit = {},
    onFocus: (() -> Unit)? = null
) {
    var hasFocus by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .height(if (hasFocus) Dimens.hCardHeightFocus else Dimens.hCardHeight)
            .border(
                width = if (hasFocus) Dimens.dp2 else Dimens.zero,
                shape = RoundedCornerShape(Dimens.dp6),
                color = if (hasFocus) Color.White else Color.Transparent
            )
            .aspectRatio(2.0f)
            .onFocusChanged { focusState ->
                if (hasFocus != focusState.hasFocus) {
                    hasFocus = focusState.hasFocus
                    if (focusState.hasFocus) onFocus?.invoke()
                }
            },
        shape = RoundedCornerShape(Dimens.dp4),
        elevation = CardDefaults.elevatedCardElevation(Dimens.dp4),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = movie.url.takeIf { !it.isNullOrEmpty() } ?: AppConstants.noUrlLink,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(if (hasFocus) Dimens.dp3 else Dimens.zero)
                    .clip(RoundedCornerShape(Dimens.dp4))
            )
        }
    }
}

