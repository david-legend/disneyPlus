package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MovieItem(
    painter: Painter,
    contentDescription: String,
    width: Dp = 100.dp,
    height: Dp = 150.dp,
    borderRadius: Dp = 18.dp,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = Modifier
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(borderRadius))
    )
}