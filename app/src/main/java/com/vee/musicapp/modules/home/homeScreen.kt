package com.vee.musicapp.modules.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vee.musicapp.data.models.Category

@Composable
fun HomeScreen(uiState: State<HomeState<List<Category>>>, reloadHomeData: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        when (uiState.value) {
            is HomeState.Loading -> {
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    Box(Modifier.align(Alignment.Center)) {
                        CircularProgressIndicator()
                    }
                }
            }

            is HomeState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val data = (uiState.value as HomeState.Success).data
                        val pageData = data.first()
                        Box(
                            modifier = Modifier.weight(.8f)
                        ) {
                            HorizontalPageView(pageData)
                        }
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            bottomView(data)
                        }
                    }
                }
            }

            is HomeState.NetworkError -> {
                val message = (uiState as HomeState.Error).message
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    Box(Modifier.align(Alignment.Center)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = message, color = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { reloadHomeData?.invoke() }) {
                                Text("Retry")
                            }
                        }
                    }
                }

            }

            is HomeState.Error -> {
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    Box(Modifier.align(Alignment.Center)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "No Internet Connection!", color = MaterialTheme.colorScheme.error)
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { reloadHomeData?.invoke() }) {
                                Text("Retry")
                            }
                        }
                    }
                }

            }
        }

        innerPadding
    }
}

@Composable
fun bottomView(items: List<Category>) {
    //    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = 80.dp
        ),
    ) {
        items(items, key = { it.id }) { item ->
            Box {
                Column {
                    Text(
                        text = item.name, style = TextStyle(
                            fontSize = 16.sp, fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                    )
                    val nMovies = remember { mutableStateOf(item.movies) }
                    if (item.type == "H") HorizontalMovieList(nMovies.value)
                    if (item.type == "V") VerticalMovieList(nMovies.value)
                    Box(Modifier.height(8.dp))
                }
            }
        }


//        Box(
//            modifier = Modifier.padding(start = 10.dp, top = 40.dp)
//        ) {
//            StaticFocusedView(
//                selectedShow,
//                isHorizontal = true
//            )
    }
}