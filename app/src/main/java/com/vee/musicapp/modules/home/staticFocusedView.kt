package com.vee.musicapp.modules.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import com.vee.musicapp.data.models.Movie

@Composable
fun StaticFocusedView(selectedShow: Movie?, isHorizontal: Boolean = true) {
    var position by remember { mutableStateOf(Offset.Zero) }
    if (isHorizontal) Box(
        modifier = Modifier
            .height(106.dp)
            .background(Color.Transparent)
            .aspectRatio(2.0f)
            .border(3.dp, Color.White, shape = RoundedCornerShape(8.dp))
            .onGloballyPositioned { layoutCoordinates ->
                position = layoutCoordinates.positionInRoot() // Get position relative to root
            }
//            .focusable()// Makes it focusable but static
    ){
        if(selectedShow!=null)
        MovieCardH(  selectedShow) {

        }
    }
    if (!isHorizontal) Box(
        modifier = Modifier
            .height(200.dp)
            .background(Color.Transparent)
            .aspectRatio(.66f)
            .border(3.dp, Color.White, shape = RoundedCornerShape(8.dp))
            .onGloballyPositioned { layoutCoordinates ->
                position = layoutCoordinates.positionInRoot() // Get position relative to root
            }.focusable()// Makes it focusable but static
    ){
        if(selectedShow!=null)
            MovieCardV (  selectedShow) {}
    }
    //check the coordinates of static view on mobile screen
//    Text(
//        text = "Position: ${position.x}, ${position.y}",
//        modifier = Modifier.padding(16.dp)
//    )
}
