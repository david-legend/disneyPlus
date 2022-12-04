package com.davidcobbina.disneyplus.ui.components.shimmers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun ImageShimmerItem(
    brush: Brush, modifier: Modifier = Modifier,
    width: Dp = 150.dp,
    height: Dp = 200.dp,
    borderRadius: Dp = 18.dp,
) {
    Spacer(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(borderRadius))
            .background(brush)
    )
}
