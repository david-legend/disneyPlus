package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.model.Genre
import com.davidcobbina.disneyplus.data.model.Movie
import com.davidcobbina.disneyplus.data.model.MovieCredits
import com.davidcobbina.disneyplus.data.model.MovieDetail
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.model.DisneyMovie
import com.davidcobbina.disneyplus.ui.components.*
import com.davidcobbina.disneyplus.ui.theme.grey600
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun TrailerAndInfoSection(
    hasMovieDescription: Boolean = false,
    movie: Movie,
    genres: List<Genre>,
//    movieDetail: MovieDetail?,
    movieCredits: MovieCredits?,
) {
    val paddingSpacing = 16.dp
    val containerPadding = 8
    val windowInfo = rememberWindowInfo()
    val screenWidth = windowInfo.screenWidth - (paddingSpacing.value + containerPadding)
    val itemWidth: Dp = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
        (screenWidth / 2).dp else (screenWidth / 4).dp
    DisneyPlusContainer(
        title = {
            RatingTitle(
                title = movie.getMovieTitle(),
                painter = painterResource(id = R.drawable.imdb),
                rating = movie.voteAverage.toString()
            )
        },
        content = {
            Column(
//                modifier = Modifier.width(screenWidth.dp)
            ) {
                if (hasMovieDescription) {
                    MovieDescriptionSection(
                        description = movie.overview,
                        genres = genres
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                }
//                LazyRow(
//                    contentPadding = PaddingValues(horizontal = paddingSpacing),
//                    content = {
//                        itemsIndexed(trailers) { _, item ->
//                            Trailer(
//                                painter = painterResource(id = item.movieCover)
//                            )
//                            Spacer(modifier = Modifier.width(16.dp))
//                        }
//                    },
//                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.padding(start = paddingSpacing)
                ) {
                    FlowRow(
                        mainAxisSize = SizeMode.Expand,
                        crossAxisSpacing = paddingSpacing,
                    ) {

                        if (movieCredits != null) {
                            for (cast in movieCredits.primaryCast) {
                                if (cast.names.isNotEmpty()) {
                                    TextList(
                                        title = cast.type,
                                        children = cast.names,
                                        modifier = Modifier
                                            .width(itemWidth)
                                    )
                                }
                            }
                        }

                        Box(
                            contentAlignment = Alignment.BottomStart,
                            modifier = Modifier.width(itemWidth)
                        ) {
                            BorderedBox(
                                modifier = Modifier
                                    .width(100.dp),
                                child = {
                                    Text(
                                        text = "PG - 13",
                                        style = MaterialTheme.typography.titleSmall.copy(color = grey600)
                                    )
                                })
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        },
    )

}