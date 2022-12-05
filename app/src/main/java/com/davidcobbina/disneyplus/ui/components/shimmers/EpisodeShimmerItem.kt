package com.davidcobbina.disneyplus.ui.components.shimmers

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun EpisodeGridShimmer(numberOfEpisodes: Int) {
    val windowInfo = rememberWindowInfo()
    val itemWidth =
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) windowInfo.screenWidthDp else ((windowInfo.screenWidth / 2) - 40).dp
    val spacing = 16.dp
    val imageWidth = itemWidth / 2

    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        Column {
            for (i in 1..numberOfEpisodes) {
                AnimatedShimmer {
                    EpisodeShimmerItem(it)
                    Spacer(modifier = Modifier.height(spacing))
                }
            }
        }
    } else {
        FlowRow(
            mainAxisSpacing = spacing,
            mainAxisSize = SizeMode.Expand,
            crossAxisSpacing = spacing
        ) {
            for (i in 1..numberOfEpisodes) {
                AnimatedShimmer {
                    EpisodeShimmerItem(
                        it,
                        itemWidth = itemWidth,
                        imageWidth = imageWidth,
                        imageHeight = imageWidth
                    )
                    Spacer(modifier = Modifier.width(spacing))
                }
            }
        }
    }
}

@Composable
fun EpisodeShimmerItem(
    brush: Brush,
    windowInfo: WindowInfo = rememberWindowInfo(),
    itemWidth: Dp = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) windowInfo.screenWidthDp else (windowInfo.screenWidth / 2).dp,
    imageWidth: Dp = (windowInfo.screenWidth / 2).dp,
    imageHeight: Dp = imageWidth
) {

    Row(
        modifier = Modifier
            .width(itemWidth)
    ) {
        ImageShimmerItem(brush = brush, width = imageWidth, height = imageHeight)
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.padding(top = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            TextListShimmer(
                brush = brush,
                rowHorizontalArrangement = Arrangement.Start,
                numberOfTexts = 2,
                textWidth = 0.3f
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextShimmer(brush = brush, height = 8.dp)
            Spacer(modifier = Modifier.height(16.dp))
            TextListShimmer(
                brush = brush,
                textHeight = 8.dp,
                isVertical = true,
                rowHorizontalArrangement = Arrangement.Start,
                numberOfTexts = 3,
            )
        }
    }
}