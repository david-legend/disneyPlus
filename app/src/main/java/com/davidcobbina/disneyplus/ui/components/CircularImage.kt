package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R

@Composable
fun CircularImage(
    painter: Painter,
    contentDescription: String,
    imageTitle: String = "",
    contentScale: ContentScale = ContentScale.Crop,
    imageSize: Dp = 100.dp,
    hasTitle: Boolean = true
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,            // crop the image if it's not a square
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)       // add a border (optional)
        )
        if (hasTitle) {
            Box(modifier = Modifier.height(16.dp))
            Text(text = imageTitle)
        }

    }
}