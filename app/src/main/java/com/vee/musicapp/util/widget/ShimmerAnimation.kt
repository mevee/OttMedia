package com.vee.musicapp.util.widget

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vee.musicapp.ui.theme.Dimens

@Composable
fun ShimmerAnimation(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "")

    // Animate shimmer position
    val shimmerTranslate by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f, // Move shimmer effect
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = modifier) {
        val gradient = Brush.linearGradient(
            colors = listOf(
                Color.Gray.copy(alpha = 0.3f),
                Color.LightGray.copy(alpha = 0.5f),
                Color.Gray.copy(alpha = 0.3f)
            ),
            start = Offset(shimmerTranslate, 0f),
            end = Offset(shimmerTranslate + size.width / 2, size.height)
        )
        drawRoundRect(brush = gradient, size = size, cornerRadius = CornerRadius(Dimens.dp8.toPx()))
    }
}
