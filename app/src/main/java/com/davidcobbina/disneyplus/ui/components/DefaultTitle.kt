package com.davidcobbina.disneyplus.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun DefaultTitle(
    title: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    ),
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {

    Text(
        text = title,
        style = titleStyle,
        overflow = overflow,
        maxLines = maxLines,
        modifier = modifier,
        onTextLayout = onTextLayout,
    )
}