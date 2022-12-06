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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.api.ApiConstants
import com.davidcobbina.disneyplus.data.model.Movie
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
                            .clickable {
                                if (movieItem.mediaType == ApiConstants.MEDIA_TYPE_TV) {
                                    navController.navigate(
                                        route = Screen.TvSeriesDetailScreen.parseMovie(movieItem)
                                    )
                                } else {
                                    navController.navigate(
                                        route = Screen.MovieDetailScreen.parseMovie(movieItem)
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
                                .clickable {
                                    if (movieItem.mediaType == ApiConstants.MEDIA_TYPE_TV) {
                                        navController.navigate(
                                            route = Screen.TvSeriesDetailScreen.parseMovie(movieItem)
                                        )
                                    } else {
                                        navController.navigate(
                                            route = Screen.MovieDetailScreen.parseMovie(movieItem)
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