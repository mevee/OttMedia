package com.vee.musicapp.modules.immersive
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.tv.foundation.lazy.list.TvLazyColumn
import coil3.compose.AsyncImage
import com.vee.musicapp.R

@Composable
fun MyImmersiveApp() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image with Blur

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w1280/q0bCG4NX32iIEsRFZqRtuvzNCyZ.jpg",
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(modifier = Modifier.fillMaxSize().background(color = Color.Black.copy(alpha = .7f)))
        // Foreground Content with Lazy Column (for TV navigation)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(10) { index ->
                FocusableItem(text = "Item $index")
            }
        }
    }
}
