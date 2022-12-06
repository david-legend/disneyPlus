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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
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

@Composable
fun MovieItem(
    url: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    width: Dp = 150.dp,
    height: Dp = 200.dp,
    borderRadius: Dp = 18.dp,
    contentScale: ContentScale = ContentScale.Crop,
    useDefaultBaseUrl: Boolean = true


) {
    val imageUrl =
        if (useDefaultBaseUrl) stringResource(id = R.string.image_base_url) + url else url
    val description =
        if (contentDescription == null || contentDescription.isEmpty()) stringResource(
            id = R.string.movie_cover_description
        ) else contentDescription

    if (url === null) {
        MovieItem(
            painterResource(id = R.drawable.image_placeholder),
            contentDescription = description,
            contentScale = contentScale,
            modifier = modifier
                .width(width)
                .height(height)
                .clip(RoundedCornerShape(borderRadius))
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .crossfade(true)
                .build(),
            contentDescription = description,
            contentScale = contentScale,
            modifier = modifier
                .width(width)
                .height(height)
                .clip(RoundedCornerShape(borderRadius))
        )
    }

}