package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R

@Composable
fun CircularImage(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    imageTitle: String = "",
    contentScale: ContentScale = ContentScale.Crop,
    imageSize: Dp = 100.dp,
    hasTitle: Boolean = true,
    titleStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(
        color = MaterialTheme.colorScheme.onPrimary
    )
) {

    Box{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = contentScale,            // crop the image if it's not a square
                modifier = imageModifier
                    .size(imageSize)
                    .clip(CircleShape)       // add a border (optional)
            )
            if (hasTitle) {
                Box(modifier = Modifier.height(16.dp))
                Text(text = imageTitle, style = titleStyle)
            }
        }
    }
}