package com.vee.musicapp.modules.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.BringIntoViewSpec
import androidx.compose.foundation.gestures.LocalBringIntoViewSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProvideLazyListPivotOffset(
    parentFraction: Float = 0.3f,
    childFraction: Float = 0f,
    content: @Composable () -> Unit,
) {
    val bringIntoViewSpec = object : BringIntoViewSpec {
        override fun calculateScrollDistance(
            offset: Float,
            size: Float,
            containerSize: Float
        ): Float = calculatePivotOffset(
            parentFraction = parentFraction,
            childFraction = childFraction,
            offset = offset,
            size = size,
            containerSize = containerSize
        )
    }
    CompositionLocalProvider(
        LocalBringIntoViewSpec provides bringIntoViewSpec,
        content = content,
    )
}


private fun calculatePivotOffset(
    parentFraction: Float,
    childFraction: Float,
    offset: Float,
    size: Float,
    containerSize: Float
): Float {
    val childSmallerThanParent = size <= containerSize
    val initialTargetForLeadingEdge =
        parentFraction * containerSize - (childFraction * size)
    val spaceAvailableToShowItem = containerSize - initialTargetForLeadingEdge

    val targetForLeadingEdge =
        if (childSmallerThanParent && spaceAvailableToShowItem < size) {
            containerSize - size
        } else {
            initialTargetForLeadingEdge
        }

    return offset - targetForLeadingEdge
}
