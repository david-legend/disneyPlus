package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.components.TextListWithDots
import com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components.*
import kotlinx.coroutines.launch
import com.davidcobbina.disneyplus.data.model.Movie
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.shimmers.AnimatedShimmer
import com.davidcobbina.disneyplus.ui.components.shimmers.TextListShimmer
import com.davidcobbina.disneyplus.ui.components.shimmers.TrailerAndInfoShimmer


@Composable
@ExperimentalMaterialApi
fun MovieDetailScreen(
    navController: NavHostController,
    movie: Movie,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val isMovieDetailLoading by viewModel.movieDetailLoading.collectAsState()
    val movieDetailState by viewModel.movieDetail.collectAsState()

    val isSimilarMoviesLoading by viewModel.similarMoviesLoading.collectAsState()
    val similarMoviesState by viewModel.similarMovies.collectAsState()

//    val movieCreditsLoading by viewModel.movieCreditsLoading.collectAsState()
    val movieCredits by viewModel.movieCredits.collectAsState()

    val isTrailersLoading by viewModel.trailersLoading.collectAsState()
    val trailersState by viewModel.movieTrailers.collectAsState()

    val paddingSpacing = dimensionResource(id = R.dimen.spacingSm)
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = movie.id) {
        viewModel.getMovieDetail(movie.id.toString())
        viewModel.getRecommendations(movie.id.toString())
        viewModel.getMovieCredits(movie.id.toString())
        viewModel.getMovieTrailers(movie.id.toString())
    }

    lifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.movieDetailEvent.collect { event ->
            when (event) {
                is MovieDetailViewModel.MovieDetailEvent.NavigateToHomeScreen -> {
                    navController.popBackStack()

                }
                is MovieDetailViewModel.MovieDetailEvent.NavigateToSimilarMovie -> {
                    navController.navigate(
                        route = Screen.MovieDetailScreen.parseMovie(event.movie)
                    )
                }
            }
        }
    }

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
            MoreActionsSheet(
                sheetState = sheetState,
                title = movie.getMovieTitle(),
                actionsList = viewModel.data.actionList
            )
        }
    ) {
        Box(modifier = Modifier.clickable {
            scope.launch {
                if (sheetState.isExpanded) {
                    sheetState.collapse()
                }
            }
        }) {
            LazyColumn {
                item {
                    MovieDetailHeaderSection(
                        movieCoverUrl = movie.posterPath,
                        mediaType = movie.mediaType,
                        contentDescription = movie.getMovieTitle(),
                        onMoreClick = {
                            scope.launch {
                                if (sheetState.isCollapsed) {
                                    sheetState.expand()
                                }
                            }
                        })
                    Spacer(modifier = Modifier.height(paddingSpacing))

                    if (isMovieDetailLoading) {
                        AnimatedShimmer {
                            TextListShimmer(it, 4, textWidth = 0.15f)
                        }
                    } else {
                        TextListWithDots(texts = movieDetailState?.metaData ?: emptyList())
                    }

                    Spacer(modifier = Modifier.height(paddingSpacing))
                    if (isMovieDetailLoading) {
                        TrailerAndInfoShimmer()
                    } else {
                        TrailerAndInfoSection(
                            hasMovieDescription = true,
                            movie = movie,
                            genres = movieDetailState?.genres ?: emptyList(),
                            movieCredits = movieCredits,
                            trailers = trailersState,
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingXXs)))
                }
                item {
                    SimilarMoviesSection(
                        isLoading = isSimilarMoviesLoading,
                        suggestedMovieList = similarMoviesState, onClick = {
                            viewModel.onNavigateToSimilarMovie(it)
                        }
                    )
                }
            }
            CircularIconButton(
                child = {
                    CustomIcon(
                        contentDescription = stringResource(R.string.add_button_content_description),
                        iconColor = Color.White,
                        icon = Icons.Filled.Close,
                    )
                },
                buttonColor = MaterialTheme.colorScheme.surface,
                onClick = {

                    viewModel.onCloseButtonPressed()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(horizontal = paddingSpacing, vertical = paddingSpacing)

            )
        }
    }

}


