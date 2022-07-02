package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R

@Composable
fun MovieItem(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    width: Dp = 150.dp,
    height: Dp = 200.dp,
    borderRadius: Dp = 18.dp,
    contentScale: ContentScale = ContentScale.Crop,

    ) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(borderRadius))
    )
}