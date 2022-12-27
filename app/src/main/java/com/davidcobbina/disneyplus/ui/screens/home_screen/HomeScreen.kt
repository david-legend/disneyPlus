package com.davidcobbina.disneyplus.ui.screens.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val avatars by viewModel.avatars.collectAsState()
    val avatarCategories by viewModel.avatarCategories.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()
    val isRecommendedMoviesLoading by viewModel.recommendedMoviesLoading.collectAsState()
    val isMarvelMoviesLoading by viewModel.marvelMoviesLoading.collectAsState()
    val isStarWarsMoviesLoading by viewModel.starWarsMoviesLoading.collectAsState()
    val isTrendingMoviesLoading by viewModel.trendingMoviesLoading.collectAsState()

    val recommendedMovies by viewModel.recommendedMovies.collectAsState()
    val marvelMovies by viewModel.marvelMovies.collectAsState()
    val starWarsMovies by viewModel.starWarsMovies.collectAsState()
    val trendingMovies by viewModel.trendingMovies.collectAsState()

    val windowInfo = rememberWindowInfo()
    val screenPadding =
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
            dimensionResource(id = R.dimen.paddingNone) else dimensionResource(id = R.dimen.paddingSmall)
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.homeScreenEvents.collect { event ->
            when (event) {
                is HomeViewModel.HomeScreenEvent.ChangeAvatarCategory -> {
                    viewModel.updateCategory(event.category)
                }
                is HomeViewModel.HomeScreenEvent.UpdateAvatar -> {
                    viewModel.updateProfile(event.avatar)
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
            ChooseAvatarSheetContent(
                sheetState,
                avatars,
                avatarCategories,
                userProfile = userProfile,
                onCategorySelected = { category -> viewModel.onCategoryChanged(category) },
                onAvatarUpdate = { avatar -> viewModel.onAvatarUpdate(avatar) }
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
                        recommendedMovies,
                        isVertical = false,
                        isLoading = isRecommendedMoviesLoading
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                    MovieListSection(
                        navController,
                        stringResource(id = R.string.trending_movies),
                        trendingMovies,
                        isVertical = false,
                        isLoading = isTrendingMoviesLoading
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                    MovieListSection(
                        navController,
                        stringResource(id = R.string.marvel),
                        marvelMovies,
                        isVertical = false,
                        isLoading = isMarvelMoviesLoading
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                    MovieListSection(
                        navController,
                        stringResource(id = R.string.star_wars),
                        starWarsMovies,
                        isVertical = false,
                        isLoading = isStarWarsMoviesLoading
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                }
            }

            Box(modifier =  Modifier
                .align(Alignment.TopEnd)) {
                CircularImage(
                    painter = painterResource(userProfile.avatar),
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
}



