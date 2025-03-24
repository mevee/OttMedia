package com.vee.musicapp.modules.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.ui.theme.Dimens

@Composable
fun HomeScreen(
    uiState: State<HomeState<List<Category>>>,
    reloadHomeData: () -> Unit,
    onItemClicked: (railId: String, Movie) -> Unit,
    onItemScrolled: (railId: String, Movie) -> Unit,
) {
    val state = rememberUpdatedState(uiState.value) // Optimized state usage

    LaunchedEffect(Unit) {
        reloadHomeData()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (val currentState = state.value) { // Avoid redundant casting
                is HomeState.Loading -> LoadingView()
                is HomeState.Success -> SuccessView(currentState.data, onItemClicked, onItemScrolled)
                is HomeState.NetworkError -> ErrorView("No Internet Connection!", reloadHomeData)
                is HomeState.Error -> ErrorView(currentState.message, reloadHomeData)
            }
        }
        innerPadding
    }
}

@Composable
fun LoadingView() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Loading movies and shows...",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(Dimens.dp16))
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun SuccessView(
    data: List<Category>,
    onItemClicked: (railId: String, Movie) -> Unit,
    onItemScrolled: (railId: String, Movie) -> Unit
) {
    val pageData = remember(data) { data.firstOrNull() }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(0.8f)) {
            pageData?.let { HorizontalPageView(it) }
        }
        Box(modifier = Modifier.weight(1f)) {
            BottomView(
                items = data,
                onClick = onItemClicked,
                onHovered = onItemScrolled
            )
        }
    }
}

@Composable
fun ErrorView(message: String, retryAction: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(Dimens.dp16))
        Button(onClick = retryAction) {
            Text(
                "Retry",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun BottomView(
    items: List<Category>,
    onClick: ((railId: String, Movie) -> Unit)? = null,
    onHovered: ((railId: String, Movie) -> Unit)? = null,
) {
    val state = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = Dimens.bottomPadding),
        state = state
    ) {
        items(items, key = { it.id }) { item ->
            Column {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(
                        start = Dimens.textPaddingStart,
                        top = Dimens.textPaddingTop,
                        bottom = Dimens.textPaddingBottom
                    )
                )

                when (item.type) {
                    "H" -> HorizontalMovieList(
                        movies = item.movies,
                        onClick = { onClick?.invoke(item.id, it) },
                        onHovered = { onHovered?.invoke(item.id, it) }
                    )
                    "V" -> VerticalMovieList(
                        movies = item.movies,
                        onClick = { onClick?.invoke(item.id, it) },
                        onHovered = { onHovered?.invoke(item.id, it) }
                    )
                }

                Spacer(Modifier.height(Dimens.spaceBetweenItems))
            }
        }
    }
}
