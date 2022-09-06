package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.suggestedMovieList
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.ui.components.DefaultTitle
import com.davidcobbina.disneyplus.ui.components.DisneyPlusContainer
import com.davidcobbina.disneyplus.ui.components.MovieItem
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun SimilarMoviesSection() {
    val spacing = 10.dp
    val containerPadding = 8
    val windowInfo = rememberWindowInfo()
    val screenWidth = windowInfo.screenWidth - (containerPadding + (spacing.value * 2))
    val itemWidth: Dp = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
        (screenWidth / 3).dp else (screenWidth / 6).dp
    val itemHeight: Dp = itemWidth + itemWidth / 2
    DisneyPlusContainer(
        title = {
            DefaultTitle(title = stringResource(id = R.string.more_like_this))
        },
        content = {
            FlowRow(
                mainAxisSpacing = spacing,
                modifier = Modifier.padding(horizontal = spacing),
                mainAxisSize = SizeMode.Expand,
                crossAxisSpacing = spacing
            ) {
                for (movieItem in suggestedMovieList) {
                    MovieItem(
                        painter = painterResource(id = movieItem.movieCover),
                        width = itemWidth - spacing, height = itemHeight,
                        contentDescription = stringResource(id = R.string.movie_cover_description),
                        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.paddingExtraMedium))
                    )
                }

            }
        }
    )

}