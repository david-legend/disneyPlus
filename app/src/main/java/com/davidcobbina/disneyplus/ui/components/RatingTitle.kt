package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R

@Composable
fun RatingTitle(
    title: String,
    rating: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    spacerModifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    ),
    ratingStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Normal
    ),
    contentDescription: String? = null,

    ) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        DefaultTitle(title = title, titleStyle = titleStyle)
        Spacer(modifier = spacerModifier.width(dimensionResource(id = R.dimen.paddingSmall)))
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = imageModifier
                .width(40.dp)
                .height(22.dp)
        )
        Spacer(modifier = spacerModifier.width(dimensionResource(id = R.dimen.paddingSmall)))
        Text(text = rating, style = ratingStyle)
    }
}