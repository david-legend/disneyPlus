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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.components.TextListWithDots
import com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components.*
import kotlinx.coroutines.launch


@Composable
@ExperimentalMaterialApi
fun MovieDetailScreen(navController: NavHostController) {
    val paddingSpacing = dimensionResource(id = R.dimen.spacingSm)
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
//            SeasonsListSheet(sheetState = sheetState, title = "The Mandalorian")
            MoreActionsSheet(sheetState = sheetState, title = "The Mandalorian")
        }
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
                    MovieDetailHeaderSection(onMoreClick = {
                        scope.launch {
                            if (sheetState.isCollapsed) {
                                sheetState.expand()
                            }
                        }
                    })
                    Spacer(modifier = Modifier.height(paddingSpacing))
                    TextListWithDots(texts = arrayListOf("2019", "2 Seasons", "PJ-13", "CC", "4k"))
                    Spacer(modifier = Modifier.height(paddingSpacing))
                    SeriesListSection(
                        onHeaderClick = {
                            scope.launch {
                                if (sheetState.isCollapsed) {
                                    sheetState.expand()
                                }
                            }
                        }
                    )
//                    Spacer(modifier = Modifier.height(paddingSpacing))
//                    TrailerAndInfoSection(hasMovieDescription = true)
//                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingXXs)))
//                    SimilarMoviesSection()

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
                onClick = { navController.popBackStack() },
                modifier = Modifier

                    .align(Alignment.TopEnd)
                    .padding(horizontal = paddingSpacing, vertical = paddingSpacing)

            )
        }
    }

}

