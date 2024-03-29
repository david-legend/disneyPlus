package com.davidcobbina.disneyplus.ui.screens.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.remote.api.ApiConstants
import com.davidcobbina.disneyplus.data.remote.model.Movie
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.MovieItem
import com.davidcobbina.disneyplus.ui.components.shimmers.AnimatedShimmer
import com.davidcobbina.disneyplus.ui.components.shimmers.ImageShimmerItem


@Composable
fun MovieListSection(
    navController: NavHostController,
    sectionTitle: String,
    movieItems: List<Movie>,
    isLoading: Boolean = false,
    isVertical: Boolean = true,
    width: Dp = 150.dp,
    height: Dp = 200.dp,
) {

    if (isVertical) {
        LazyColumn() {
            item {
                Text(
                    text = sectionTitle, style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp, fontWeight = FontWeight.W600
                    )
                )
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingSm)))
            }
            if (isLoading) {
                item {
                    repeat(8) {
                        AnimatedShimmer {
                            ImageShimmerItem(
                                brush = it,
                                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.paddingExtraMedium))
                            )
                        }

                    }
                }
            } else {
                itemsIndexed(movieItems) { _, movieItem ->
                    MovieItem(
                        url = movieItem.posterPath,
                        contentDescription = movieItem.getMovieTitle(),
                        modifier = Modifier
                            .padding(bottom = dimensionResource(id = R.dimen.paddingExtraMedium))
                            .width(width)
                            .height(height)
                            .clickable {
                                if (movieItem.mediaType == ApiConstants.MEDIA_TYPE_TV) {
                                    navController.navigate(
                                        route = Screen.TvSeriesDetailScreen.passTvSeries(movieItem)
                                    )
                                } else {
                                    navController.navigate(
                                        route = Screen.MovieDetailScreen.passMovie(movieItem)
                                    )
                                }
                            }
                    )
                }
            }

        }
    } else {
        Column() {
            Text(
                text = sectionTitle, style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp, fontWeight = FontWeight.W600
                )
            )
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingSm)))
            LazyRow() {
                if (isLoading) {
                    item {
                        repeat(8) {
                            AnimatedShimmer {
                                ImageShimmerItem(
                                    brush = it,
                                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.paddingExtraMedium))
                                )
                            }
                        }
                    }
                } else {
                    itemsIndexed(movieItems) { _, movieItem ->

                        MovieItem(
                            url = movieItem.posterPath,
                            contentDescription = movieItem.getMovieTitle(),
                            modifier = Modifier
                                .padding(end = dimensionResource(id = R.dimen.paddingExtraMedium))
                                .width(width)
                                .height(height)
                                .clickable {
                                    if (movieItem.mediaType == ApiConstants.MEDIA_TYPE_TV) {
                                        navController.navigate(
                                            route = Screen.TvSeriesDetailScreen.passTvSeries(
                                                movieItem
                                            )
                                        )
                                    } else {
                                        navController.navigate(
                                            route = Screen.MovieDetailScreen.passMovie(movieItem)
                                        )
                                    }
                                }
                        )
                    }
                }
            }
        }
    }

}