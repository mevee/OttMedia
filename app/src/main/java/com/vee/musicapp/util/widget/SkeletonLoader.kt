package com.vee.musicapp.util.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.vee.musicapp.ui.theme.Dimens

@Composable
fun SkeletonLoader() {
    Column(modifier = Modifier.padding(Dimens.dp16)) {
        Row {
            ShimmerAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.dp200)
                    .weight(.3f)
                    .padding(horizontal = Dimens.dp16)
                    .clip(RoundedCornerShape(Dimens.dp4))
            )
            ShimmerAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.dp200)
                    .weight(.7f)
                    .padding(horizontal = Dimens.dp16)
                    .clip(RoundedCornerShape(Dimens.dp4))
            )
        }
        Spacer(modifier = Modifier.height(Dimens.dp8))

        repeat(3) { // Show 5 placeholder items
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.dp16),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

                ) {

                ShimmerAnimation(
                    modifier = Modifier
                        .width(Dimens.dp200)
                        .height(Dimens.dp100)
                        .padding(end = Dimens.dp8)
                        .clip(RoundedCornerShape(Dimens.dp4))
                )
                ShimmerAnimation(
                    modifier = Modifier
                        .width(Dimens.dp200)
                        .height(Dimens.dp100)
                        .padding(end = Dimens.dp8)

                        .clip(RoundedCornerShape(Dimens.dp4))
                )
                ShimmerAnimation(
                    modifier = Modifier
                        .width(Dimens.dp200)
                        .height(Dimens.dp100)
                        .padding(end = Dimens.dp8)
                        .clip(RoundedCornerShape(Dimens.dp4))
                )
                ShimmerAnimation(
                    modifier = Modifier
                        .width(Dimens.dp200)
                        .height(Dimens.dp100)
                        .padding(end = Dimens.dp8)
                        .clip(RoundedCornerShape(Dimens.dp4))
                )
            }
            Spacer(modifier = Modifier.height(Dimens.dp8))
        }
    }
}
