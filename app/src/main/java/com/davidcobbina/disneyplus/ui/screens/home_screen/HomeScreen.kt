package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.DisneyMovie
import com.davidcobbina.disneyplus.data.suggestedMovieList
import com.davidcobbina.disneyplus.ui.components.CircularImage
import com.davidcobbina.disneyplus.ui.components.MovieItem
import com.davidcobbina.disneyplus.ui.screens.home_screen.components.ChooseAvatarSheetContent
import kotlinx.coroutines.launch


@Composable
@ExperimentalMaterialApi
fun HomeScreen() {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background,
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.borderRadiusExtraLarge),
            topEnd = dimensionResource(id = R.dimen.borderRadiusExtraLarge)
        ),
        sheetContent = {
            ChooseAvatarSheetContent(sheetState)
        },
    ) {
        Box(modifier = Modifier.clickable {
            scope.launch {
                if (sheetState.isExpanded) {
                    sheetState.collapse()
                }
            }
        }) {
            LazyColumn() {
                item {
                    Box(modifier = Modifier.height(180.dp))
                    HeaderSection()
                    Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
                    MovieListSection(
                        stringResource(id = R.string.movie_suggestion_title),
                        suggestedMovieList,
                        isVertical = false
                    )
                    Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
                    MovieListSection(
                        stringResource(id = R.string.keep_watching),
                        suggestedMovieList,
                        isVertical = false
                    )
                    Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
                    MovieListSection(
                        stringResource(id = R.string.your_watchlist),
                        suggestedMovieList,
                        isVertical = false
                    )
                    Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
                    MovieListSection(
                        stringResource(id = R.string.movies),
                        suggestedMovieList,
                        isVertical = false
                    )
                    Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
                    MovieListSection(
                        stringResource(id = R.string.marvel),
                        suggestedMovieList,
                        isVertical = false
                    )
                    Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
                    MovieListSection(
                        stringResource(id = R.string.star_wars),
                        suggestedMovieList,
                        isVertical = false
                    )
                    Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
                }
            }
            CircularImage(
                painter = painterResource(R.drawable.jasmine),
                imageSize = 50.dp,
                hasTitle = false,
                contentDescription = stringResource(R.string.profile_content_description),
                imageTitle = stringResource(R.string.megan),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.paddingLarge),
                        vertical = dimensionResource(id = R.dimen.paddingLarge)
                    )
                    .clickable {
                        scope.launch {
                            if (sheetState.isCollapsed) {
                                sheetState.expand()
                            }
                        }
                    }
            )
        }
    }
}


@Composable
fun HeaderSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.paddingLarge))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.everything),
                style = MaterialTheme.typography.headlineMedium
            )
            Icon(
                Icons.Filled.KeyboardArrowDown,
                contentDescription = stringResource(id = R.string.categorise_movie_dropdown),
                modifier = Modifier.size(dimensionResource(id = R.dimen.iconSizeLarge)),
                tint = MaterialTheme.colorScheme.onPrimary

            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
        IconButton(
            onClick = { /*TODO*/ }, Modifier.background(
                color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.borderRadiusExtraLarge)
                )
            )
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_movie_icon),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.iconSizeMedium)),
                tint = MaterialTheme.colorScheme.onSecondary,
            )
        }
        Box(modifier = Modifier.width(dimensionResource(id = R.dimen.marginMedium)))
        IconButton(
            onClick = { /*TODO*/ }, Modifier.background(
                color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.borderRadiusExtraLarge),
                )
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_downward),
                contentDescription = stringResource(id = R.string.download_icon),
                modifier = Modifier.size(dimensionResource(id = R.dimen.iconSizeMedium)),
                tint = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}

@Composable
fun MovieListSection(
    sectionTitle: String,
    movieItems: List<DisneyMovie>,
    isVertical: Boolean = true
) {
    if (isVertical) {
        LazyColumn() {
            item {
                Text(text = sectionTitle, style = MaterialTheme.typography.titleLarge)
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginMedium)))
            }
            itemsIndexed(movieItems) { _, movieItem ->
                MovieItem(
                    painter = painterResource(id = movieItem.movieCover),
                    contentDescription = stringResource(id = R.string.movie_cover_description),
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.paddingExtraMedium))
                )
            }
        }
    } else {
        Column() {
            Text(text = sectionTitle, style = MaterialTheme.typography.titleLarge)
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginMedium)))
            LazyRow() {
                itemsIndexed(movieItems) { _, movieItem ->
                    MovieItem(
                        painter = painterResource(id = movieItem.movieCover),
                        contentDescription = stringResource(id = R.string.movie_cover_description),
                        modifier = Modifier
                            .padding(end = dimensionResource(id = R.dimen.paddingExtraMedium))

                    )
                }
            }
        }
    }

}

