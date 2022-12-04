package com.davidcobbina.disneyplus.ui.components.shimmers

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun GridShimmerItem(
    rowItemCount: Int = 2,
    gridItems: Int = 6
) {
    val spacing = 10.dp
    val containerPadding = 8
    val windowInfo = rememberWindowInfo()
    val screenWidth = windowInfo.screenWidth - (containerPadding + (spacing.value * 2))
    val itemWidth: Dp = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
        (screenWidth / rowItemCount).dp else (screenWidth / (rowItemCount * 2)).dp
    val itemHeight: Dp = itemWidth + (itemWidth / 2)

    FlowRow(
        mainAxisSpacing = spacing,
        modifier = Modifier.padding(horizontal = spacing),
        mainAxisSize = SizeMode.Expand,
        crossAxisSpacing = spacing
    ) {
        for (i in 1..gridItems) {
            AnimatedShimmer {
                ImageShimmerItem(
                    brush = it,
                    width = itemWidth - spacing,
                    height = itemHeight
                )
            }
        }

    }
}