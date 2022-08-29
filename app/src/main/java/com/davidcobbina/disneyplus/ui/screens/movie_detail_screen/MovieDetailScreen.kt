package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components.MovieDetailHeaderSection
import com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components.SimilarMoviesSection



//TODO:: More Like this Section

//TODO:: BottomSheet Show
//TODO:: Movie Description
//TODO:: Series List
//TODO:: More Like This Section

// Done
//TODO:: Header Section
//      1. Close button to navigate back
//      2. Scrollable Images with border radius
//      5. Add button
//      4. Play button
//      5. More button
//      6. Movie Title At the Center
@Composable
fun MovieDetailScreen() {
    val paddingSpacing = dimensionResource(id = R.dimen.marginMedium)
    Box {
        LazyColumn() {
            item {
                MovieDetailHeaderSection()
                SimilarMoviesSection()
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
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(horizontal = paddingSpacing, vertical = paddingSpacing)
                .clickable {
                    //pop current screen off
                }
        )
    }
}

