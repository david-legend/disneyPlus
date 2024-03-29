package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.remote.model.Trailer
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo

@Composable
fun TrailerPreview(
    data: Trailer,
    modifier: Modifier = Modifier,
    type: String = stringResource(id = R.string.trailer),
    typeStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(color = Color.White),
    duration: String = stringResource(id = R.string.trailer_duration),
    durationStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    imageModifier: Modifier = Modifier,
) {
    val windowInfo = rememberWindowInfo()
    val screenWidth = windowInfo.screenWidth
    val trailerWidth =
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) (screenWidth * 0.75) else (screenWidth * 0.4)
    val trailerHeight = trailerWidth * 0.65
    Box(modifier = modifier) {
        MoviePreview(
            imageUrl = data.imageUrl, contentDescription = data.name, imageModifier = imageModifier
                .width(trailerWidth.dp)
                .height(trailerHeight.dp)
                .shadow(
                    elevation = dimensionResource(id = R.dimen.elevationSmall),
                    shape = RoundedCornerShape(8.dp)
                )
        )
        Sticker(
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .padding(
                    start = dimensionResource(id = R.dimen.paddingExtraMedium),
                    bottom = dimensionResource(id = R.dimen.paddingExtraMedium)
                ),
            child = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(type, style = typeStyle)
                    Dot(dotSize = 2.dp, dotColor = MaterialTheme.colorScheme.onPrimary)
                    Text(duration, style = durationStyle)
                }
            },
        )

    }
}
