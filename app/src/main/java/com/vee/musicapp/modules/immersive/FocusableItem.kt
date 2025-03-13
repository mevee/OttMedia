package com.vee.musicapp.modules.immersive

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FocusableItem(text: String) {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(200.dp)
            .height(100.dp)
            .focusable()
            .focusRequester(focusRequester)
            .onFocusChanged { isFocused.value = it.isFocused }
            .background(if (isFocused.value) Color.White.copy(alpha = 0.2f) else Color.Transparent)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White, fontSize = if (isFocused.value) 24.sp else 20.sp)
    }
}
