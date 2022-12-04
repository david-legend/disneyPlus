package com.davidcobbina.disneyplus.ui.components.shimmers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TextShimmer(
    brush: Brush, modifier: Modifier = Modifier,
    width: Float = 0.2f,
    height: Dp = 12.dp,
    borderRadius: Dp = 6.dp,
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth(width)
            .height(height)
            .clip(RoundedCornerShape(borderRadius))
            .background(brush)
    )
}