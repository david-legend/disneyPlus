package com.davidcobbina.disneyplus.ui.screens.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.DisneyMovie
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.MovieItem


@Composable
fun MovieListSection(
    navController: NavHostController,
    sectionTitle: String,
    movieItems: List<DisneyMovie>,
    isVertical: Boolean = true
) {
    if (isVertical) {
        LazyColumn() {
            item {
                Text(text = sectionTitle, style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp, fontWeight = FontWeight.W600
                ))
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingSm)))
            }
            itemsIndexed(movieItems) { _, movieItem ->
                MovieItem(
                    painter = painterResource(id = movieItem.movieCover),
                    contentDescription = stringResource(id = R.string.movie_cover_description),
                    modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.paddingExtraMedium))
                        .clickable {
                            navController.navigate(route = Screen.MovieDetailScreen.route)
                        }
                )
            }
        }
    } else {
        Column() {
            Text(text = sectionTitle, style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp, fontWeight = FontWeight.W600
            ))
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingSm)))
            LazyRow() {
                itemsIndexed(movieItems) { _, movieItem ->
                    MovieItem(
                        painter = painterResource(id = movieItem.movieCover),
                        contentDescription = stringResource(id = R.string.movie_cover_description),
                        modifier = Modifier
                            .padding(end = dimensionResource(id = R.dimen.paddingExtraMedium))
                            .clickable {
                                navController.navigate(route = Screen.MovieDetailScreen.route)
                            }
                    )
                }
            }
        }
    }

}