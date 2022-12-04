package com.davidcobbina.disneyplus.ui.components.shimmers

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TextListShimmer(
    brush: Brush,
    numberOfTexts: Int,
    modifier: Modifier = Modifier,
    textWidth: Float = 1f,
    textHeight: Dp = 12.dp,
    textSpacing: Dp = 12.dp,
    isVertical: Boolean = false,
    columnHorizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    rowVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    columnVerticalArrangement: Arrangement.Vertical = Arrangement.Center,
    columnHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
) {
    val textLength = numberOfTexts - 1

    if (isVertical) {
        Column(
            modifier = modifier.fillMaxWidth(1f),
            verticalArrangement = columnVerticalArrangement,
            horizontalAlignment = columnHorizontalAlignment,
        ) {
            for (index in 0..textLength) {
                if (index == textLength) {
                    TextShimmer(brush = brush, width = textWidth, height = textHeight)

                } else {
                    TextShimmer(brush = brush, width = textWidth, height = textHeight)
                    Spacer(modifier = Modifier.height(textSpacing))
                }
            }
        }
    } else {
        Row(
            modifier = modifier.fillMaxWidth(1f),
            horizontalArrangement = columnHorizontalArrangement,
            verticalAlignment = rowVerticalAlignment
        ) {
            for (index in 0..textLength) {
                if (index == textLength) {
                    TextShimmer(brush = brush, width = textWidth, height = textHeight)

                } else {
                    TextShimmer(brush = brush, width = textWidth, height = textHeight)
                    Spacer(modifier = Modifier.width(textSpacing))
                }
            }
        }
    }

}