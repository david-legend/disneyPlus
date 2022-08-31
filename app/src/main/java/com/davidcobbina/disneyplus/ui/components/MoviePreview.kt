package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R


@Composable
fun MoviePreview(
    painter: Painter,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    contentDescription: String? = null,
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val trailerWidth = (screenWidth * 0.5)
    val trailerHeight = (trailerWidth * 0.7)
    Box(modifier = modifier) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.FillBounds,
            modifier = imageModifier
                .width(trailerWidth.dp)
                .height(trailerHeight.dp)
                .shadow(
                    elevation = dimensionResource(id = R.dimen.elevationSmall),
                    shape = RoundedCornerShape(16.dp)
                )
        )
        Box(
            modifier = Modifier.matchParentSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularIconButton(
                buttonColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                child = {
                    CustomIcon(
                        icon = Icons.Default.PlayArrow,
                        iconColor = Color.White,
                        iconSize = dimensionResource(id = R.dimen.iconSizeLarge)
                    )
                },
                onClick = { /*TODO*/ },
            )
        }

    }
}
