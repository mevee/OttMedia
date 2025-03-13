package com.vee.musicapp.modules.home

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vee.musicapp.data.models.Category
import com.vee.musicapp.viewmodel.MovieViewModel

@Composable
fun HomeScreen(viewModel: MovieViewModel){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.weight(.8f)
                ) {
                    val pageData by viewModel.viewPageData.observeAsState(
                        Category(name = "", type = "H", movies = emptyList())
                    )
                    HorizontalPageView(pageData)
                }
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    bottomView(viewModel)
                    innerPadding
                }
            }
        }

    }
}

@Composable
fun bottomView(viewModel: MovieViewModel) {
    val items by viewModel.homePageLiveData.observeAsState(emptyList())
    val focusId by viewModel.focusedIndex.observeAsState("")
    val selectedShow by viewModel.currentShow.observeAsState()
    val lazyListState = rememberLazyListState()

    Box {
        ProvideLazyListPivotOffset (){
            LazyColumn(
            modifier = Modifier.fillMaxSize(),
                state = lazyListState
//            pivotOffsets = PivotOffsets(0.0f)
        ) {
            items(items) { item ->
                Box {
                    Column {
                        Text(
                            text = item.name,
                            style = TextStyle(
                                fontSize = 16.sp, fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                        )
                        if (item.type == "H") HorizontalMovieList(item.movies, focusId, viewModel)
                        if (item.type == "V") VerticalMovieList(item.movies, focusId)
                        Box(Modifier.height(8.dp))
                    }
                }
            }
        }}

//        Box(
//            modifier = Modifier.padding(start = 10.dp, top = 40.dp)
//        ) {
//            StaticFocusedView(
//                selectedShow,
//                isHorizontal = true
//            )
//        }
    }
}