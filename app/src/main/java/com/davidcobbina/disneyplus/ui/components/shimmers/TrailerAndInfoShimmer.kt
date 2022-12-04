package com.davidcobbina.disneyplus.ui.components.shimmers

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.ui.components.DisneyPlusContainer
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun TrailerAndInfoShimmer() {
    val windowInfo = rememberWindowInfo()
    val paddingSpacing = 16.dp
    val containerPadding = 8
    val screenWidth = windowInfo.screenWidth - (paddingSpacing.value + containerPadding)
    val itemWidth: Dp = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
        (screenWidth ).dp else (screenWidth / 2).dp

    DisneyPlusContainer(
        title = {
            AnimatedShimmer {
                TextListShimmer(it, textWidth = 0.2f, numberOfTexts = 2, textHeight = 16.dp)
            }
        },
        content = {
            Column {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingSm)))
                //Movie Description
                AnimatedShimmer {
                    TextListShimmer(it, textWidth = 0.85f, isVertical = true, numberOfTexts = 3)
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                //Movie Genres
                AnimatedShimmer {
                    TextListShimmer(it, textWidth = 0.2f, numberOfTexts = 3)
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                Row {
                    repeat(2) {
                        AnimatedShimmer {
                            ImageShimmerItem(brush = it, width = itemWidth)
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                Row(
                    modifier = Modifier.padding(start = paddingSpacing)
                ) {
                    FlowRow(
                        mainAxisSize = SizeMode.Expand,
                        crossAxisSpacing = paddingSpacing,
                    ) {
                        VerticalTextList(0.2f)
                        VerticalTextList(0.3f)
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    )
}

@Composable
fun VerticalTextList(width: Float) {
    AnimatedShimmer {
        TextListShimmer(
            it,
            textHeight = 6.dp,
            textWidth = width,
            textSpacing = 8.dp,
            numberOfTexts = 3,
        )
    }
}