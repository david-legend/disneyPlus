package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun StackedImage(
    painter: Painter,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = imageModifier
                .offset(x = 22.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(12.dp), clip = true)
        )
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = imageModifier
                .offset(x = 12.dp)
                .shadow(elevation = 24.dp, shape = RoundedCornerShape(12.dp), clip = true)
        )
        Image(
            painter = painter, contentDescription = contentDescription,
            modifier = imageModifier.shadow(
                elevation = 40.dp,
                shape = RoundedCornerShape(12.dp),
                clip = true,
            )
        )
    }

}