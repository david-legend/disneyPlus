package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.davidcobbina.disneyplus.R

@Composable
fun TextList(
    title: String,
    children: List<String>,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.bodyLarge
        .copy(color = MaterialTheme.colorScheme.onPrimary),
    childrenStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
    maxChildren: Int = 4
) {
    val length = if (children.size > maxChildren) maxChildren - 1 else children.size - 1
    val hasShowMore = children.size > maxChildren
    Column(
        modifier = modifier
    ) {
        Text(title, style = titleStyle)
        for (index in 0..length) {
            Text(children[index], style = childrenStyle)
        }
        if (hasShowMore) {
            Text("More")
        }
    }
}

@Composable
fun TextListWithPunctuation(
    texts: List<String>,
    modifier: Modifier = Modifier,
    punctuation: String = ",",
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 16.sp
    ),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
) {
    val textLength = texts.size - 1
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        for (index in 0..textLength) {
            if (index == textLength) {
                Text(
                    text = texts[index],
                    style = textStyle,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = "${texts[index]}$punctuation ",
                    style = textStyle,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TextListWithDots(
    texts: List<String>,
    modifier: Modifier = Modifier,
    dotModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
        .copy(color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.W500),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
) {
    val textLength = texts.size - 1
    Row(
        modifier = modifier.fillMaxWidth(1f),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        for (index in 0..textLength) {
            if (index == textLength) {
                Text(
                    text = texts[index],
                    style = textStyle,
                    modifier = textModifier,
                )

            } else {
                Text(
                    text = texts[index],
                    style = textStyle,
                    modifier = textModifier,
                )
                Dot(
                    dotColor = MaterialTheme.colorScheme.onPrimary,
                    dotSize = 2.dp,
                    padding = dimensionResource(id = R.dimen.paddingSmall),
                    modifier = dotModifier
                )
            }

        }
    }
}