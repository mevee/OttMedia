package com.vee.musicapp.modules.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.data.models.Movie
import com.vee.musicapp.ui.theme.Dimens

@Composable
fun HomeScreen(
    uiState: State<HomeState<List<Category>>>,
    reloadHomeData: () -> Unit,
    onItemClicked: (Movie) -> Unit,
    onItemScrolled: (railId: String, Movie) -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (uiState.value) {
            is HomeState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeState.Success -> {
                val data = (uiState.value as HomeState.Success).data
                val pageData = data.first()
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier.weight(0.8f)) {
                        HorizontalPageView(pageData)
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        BottomView(
                            data,
                            onClick = onItemClicked,
                            onHovered = onItemScrolled,
                        )
                    }
                }
            }

            is HomeState.NetworkError, is HomeState.Error -> {
                val message = if (uiState.value is HomeState.NetworkError) {
                    "No Internet Connection!"
                } else {
                    (uiState.value as HomeState.Error).message
                }

                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = message, color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(Dimens.spacerHeight))
                        Button(onClick = { reloadHomeData() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
        innerPadding
    }
}

@Composable
fun BottomView(
    items: List<Category>,
    onClick: ((Movie) -> Unit)? = null,
    onHovered: ((railId: String, Movie) -> Unit)? = null,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = Dimens.bottomPadding),

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
                val nMovies = remember { item.movies }
                when (item.type) {
                    "H" -> HorizontalMovieList(
                        nMovies, onClick = onClick,
                        onHovered = {
                            onHovered?.invoke(item.id, it)
                        }
                    )

                    "V" -> VerticalMovieList(nMovies)
                }
                Spacer(Modifier.height(Dimens.spaceBetweenItems))
            }
        }
    }
}
