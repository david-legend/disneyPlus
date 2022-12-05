package com.davidcobbina.disneyplus.ui.screens.tv_series_detail_screen

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
import com.davidcobbina.disneyplus.data.api.ApiConstants
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.components.TextListWithDots
import com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components.*
import kotlinx.coroutines.launch
import com.davidcobbina.disneyplus.data.model.Movie
import com.davidcobbina.disneyplus.ui.components.shimmers.AnimatedShimmer
import com.davidcobbina.disneyplus.ui.components.shimmers.EpisodeGridShimmer
import com.davidcobbina.disneyplus.ui.components.shimmers.TextListShimmer
import com.davidcobbina.disneyplus.ui.components.shimmers.TrailerAndInfoShimmer


//TODO:: Fix Long Text titles
//TODO:: Populate Episode Section Data
//TODO:: Add Trailer Data
//TODO:: Add Navigation of Similar Movie Section

//LATER
//TODO:: Add Animation of Close and Open Episode Accordion

@Composable
@ExperimentalMaterialApi
fun TvSeriesDetailScreen(
    navController: NavHostController,
    movie: Movie,
    viewModel: TvSeriesDetailViewModel = hiltViewModel()
) {

    val isTvSeriesDetailLoading by viewModel.tvSeriesDetailLoading.collectAsState()
    val tvSeriesDetailState by viewModel.tvSeriesDetail.collectAsState()

    val isSimilarMoviesLoading by viewModel.similarMoviesLoading.collectAsState()
    val similarMoviesState by viewModel.similarMovies.collectAsState()

    val movieCredits by viewModel.credits.collectAsState()

    val isSeasonDetailLoading by viewModel.seasonDetailLoading.collectAsState()
    val seasonDetailState by viewModel.seasonDetail.collectAsState()

    val paddingSpacing = dimensionResource(id = R.dimen.spacingSm)
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = movie.id) {
        viewModel.getTvSeriesDetail(movie.id.toString())
        viewModel.getRecommendations(movie.id.toString())
        viewModel.getMovieCredits(movie.id.toString())
        viewModel.getTvSeriesSeasonDetail(
            movie.id.toString(),
            viewModel.seasonNumber.value.toString()
        )
    }

    lifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.tvSeriesDetailEvent.collect { event ->
            when (event) {
                is TvSeriesDetailViewModel.TvSeriesDetailEvent.NavigateToHomeScreen -> {
                    navController.popBackStack()
                }
                is TvSeriesDetailViewModel.TvSeriesDetailEvent.ChangeSeason -> {
//                    viewModel.getTvSeriesSeasonDetail(movie.id.toString(), seasonNumber)
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
            SeasonsListSheet(
                sheetState = sheetState,
                title = "The Mandalorian",
                seasonsList = tvSeriesDetailState?.seasons ?: emptyList(), onSeasonTap = {
//                    viewModel.onSeasonChange()
                }
            )
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

                    if (isTvSeriesDetailLoading) {
                        AnimatedShimmer {
                            TextListShimmer(it, 4, textWidth = 0.15f)
                        }
                    } else {
                        TextListWithDots(texts = tvSeriesDetailState?.metaData ?: emptyList())
                    }


                    Spacer(modifier = Modifier.height(paddingSpacing))
                    EpisodeGridShimmer(4)
//                    SeriesListSection(
//                        onHeaderClick = {
//                            scope.launch {
//                                if (sheetState.isCollapsed) {
//                                    sheetState.expand()
//                                }
//                            }
//                        },
//                        episodes = viewModel.data.episodes
//                    )


                    Spacer(modifier = Modifier.height(paddingSpacing))
                    if (isTvSeriesDetailLoading) {
                        TrailerAndInfoShimmer()
                    } else {
                        TrailerAndInfoSection(
                            hasMovieDescription = true,
                            movie = movie,
                            genres = tvSeriesDetailState?.genres ?: emptyList(),
                            movieCredits = movieCredits
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingXXs)))
                }
                item {
                    SimilarMoviesSection(
                        isLoading = isSimilarMoviesLoading,
                        suggestedMovieList = similarMoviesState
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


