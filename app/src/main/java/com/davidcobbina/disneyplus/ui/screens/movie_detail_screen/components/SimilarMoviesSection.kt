package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.suggestedMovieList
import com.davidcobbina.disneyplus.ui.components.DisneyPlusContainer
import com.davidcobbina.disneyplus.ui.components.MovieItem
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun SimilarMoviesSection() {
    val horizontalPadding = 10.dp
    val spacingBetweenMovies = 6.dp
    val itemWidth: Dp = (LocalConfiguration.current.screenWidthDp.dp / 3) - (horizontalPadding + spacingBetweenMovies)
    val itemHeight: Dp = itemWidth + itemWidth / 2
    DisneyPlusContainer(
        title = "More like this",
        child = {
            FlowRow(
                modifier = Modifier.padding(horizontal = horizontalPadding),
                mainAxisSize = SizeMode.Expand,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
            ) {
                for (movieItem in suggestedMovieList) {
                    MovieItem(
                        painter = painterResource(id = movieItem.movieCover),
                        width = itemWidth, height = itemHeight,
                        contentDescription = stringResource(id = R.string.movie_cover_description),
                        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.paddingExtraMedium))
                    )
                }

            }
        }
    )

}