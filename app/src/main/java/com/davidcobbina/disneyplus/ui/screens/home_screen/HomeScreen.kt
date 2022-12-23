package com.davidcobbina.disneyplus.ui.screens.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.ui.components.CircularImage
import com.davidcobbina.disneyplus.ui.screens.home_screen.components.ChooseAvatarSheetContent
import com.davidcobbina.disneyplus.ui.screens.home_screen.components.HeaderSection
import com.davidcobbina.disneyplus.ui.screens.home_screen.components.MovieListSection
import kotlinx.coroutines.launch


@Composable
@ExperimentalMaterialApi
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val avatars by homeViewModel.avatars.collectAsState()
    val recommendedMovies = homeViewModel.moviesFeed.collectAsState()
    val isRecommendedMoviesLoading = homeViewModel.moviesFeedLoading.collectAsState()

    val windowInfo = rememberWindowInfo()
    val screenPadding =
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
            dimensionResource(id = R.dimen.paddingNone) else dimensionResource(id = R.dimen.paddingSmall)
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
            ChooseAvatarSheetContent(
                sheetState,
                avatars,
                homeViewModel.data.avatarCategories
            )
        },
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    scope.launch {
                        if (sheetState.isExpanded) {
                            sheetState.collapse()
                        }
                    }
                }
                .padding(screenPadding)
        ) {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.topSpacing)))
                    HeaderSection(navController)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))

                    MovieListSection(
                        navController,
                        stringResource(id = R.string.movie_suggestion_title),
                        recommendedMovies.value,
                        isVertical = false,
                        isLoading = isRecommendedMoviesLoading.value
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                    MovieListSection(
                        navController,
                        stringResource(id = R.string.keep_watching),
                        recommendedMovies.value,
                        isVertical = false,
                        isLoading = isRecommendedMoviesLoading.value
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                    MovieListSection(
                        navController,
                        stringResource(id = R.string.your_watchlist),
                        recommendedMovies.value,
                        isVertical = false,
                        isLoading = isRecommendedMoviesLoading.value
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                    MovieListSection(
                        navController,
                        stringResource(id = R.string.movies),
                        recommendedMovies.value,
                        isVertical = false,
                        isLoading = isRecommendedMoviesLoading.value
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                    MovieListSection(
                        navController,
                        stringResource(id = R.string.marvel),
                        recommendedMovies.value,
                        isVertical = false,
                        isLoading = isRecommendedMoviesLoading.value
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                    MovieListSection(
                        navController,
                        stringResource(id = R.string.star_wars),
                        recommendedMovies.value,
                        isVertical = false,
                        isLoading = isRecommendedMoviesLoading.value
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                }
            }
            CircularImage(
                painter = painterResource(R.drawable.merida),
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



