package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.ui.components.*
import com.davidcobbina.disneyplus.ui.screens.downloads_screen.DownloadsViewModel
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import  androidx.lifecycle.viewmodel.compose.viewModel


//TODO:: Stacked Images..

@Composable
fun DownloadScreen(navController: NavHostController, viewModel: DownloadsViewModel = viewModel()) {
    val paddingSpacing = dimensionResource(id = R.dimen.spacingMd)
    val windowInfo = rememberWindowInfo()
    val screenWidthWithoutPadding = windowInfo.screenWidth - paddingSpacing.value - 20
    val itemSize =
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
            screenWidthWithoutPadding else (screenWidthWithoutPadding * 0.5)
    Box(
        modifier = Modifier
            .padding(
                vertical = paddingSpacing,
                horizontal = paddingSpacing
            )
            .fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.topSpacing)))
                } else {
                    Spacer(modifier = Modifier.height(paddingSpacing))
                }

                DownloadPageTitle()
                Spacer(modifier = Modifier.height(paddingSpacing))
                FlowRow(
                    mainAxisSize = SizeMode.Expand,
                    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                    crossAxisSpacing = dimensionResource(id = R.dimen.spacingMd),
                ) {
                    for (movie in viewModel.data.downloadedMovies) {
                        DownloadedMovieItem(
                            movieCover = painterResource(id = R.drawable.mandalorian_cover),
                            itemSize = itemSize.toInt(),
                            title = movie.title,
                            yearReleased = movie.yearReleased,
                            downloadedSize = movie.downloadedSize
                        )
                    }
                }
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
                .padding(vertical = paddingSpacing)
                .clickable {
                    //pop current screen off
                }
        )
    }

}


@Composable
fun DownloadPageTitle() {
    Column {
        TextWithIcon(
            title = stringResource(id = R.string.downloads),
            contentDescription = stringResource(id = R.string.downloads_dropdown)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginXs)))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "24 Videos",
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
            )
            Dot(
                modifier = Modifier.padding(horizontal = 4.dp),
                dotColor = MaterialTheme.colorScheme.onPrimary,
                dotSize = 2.dp
            )
            Text(
                text = "7.15GB",
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}

@Composable
fun DownloadedMovieItem(
    movieCover: Painter,
    title: String,
    yearReleased: String,
    downloadedSize: String,
    itemSize: Int,
    isSeries: Boolean = false,
    numberOfEpisodes: Int? = 0,
    imageContentDescription: String? = null,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(color = Color.White),
    subtitleStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 16.sp,
    ),
) {
    val downloadSize = if (isSeries) "Total of $downloadedSize" else downloadedSize
    val windowInfo = rememberWindowInfo()
    val movieCoverWidth = itemSize * 0.3
    val movieCoverHeight = movieCoverWidth + (movieCoverWidth / 4)

    Row(
        modifier = Modifier.width(itemSize.dp)
    ) {
        StackedImage(
            painter = movieCover,
            contentDescription = imageContentDescription,
            modifier = Modifier
                .width(movieCoverWidth.dp)
                .height(movieCoverHeight.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.height(movieCoverHeight.dp)) {
            Text(title, style = titleStyle)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(yearReleased, style = subtitleStyle)
                Dot(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    dotColor = MaterialTheme.colorScheme.onPrimary,
                    dotSize = 2.dp
                )
                Text(downloadSize, style = subtitleStyle)
            }
            //when screenWidth is not compact, change position of View Button to bottom of text
            if (windowInfo.screenWidthInfo !is WindowInfo.WindowType.Compact) {
                Spacer(modifier = Modifier.weight(weight = 1f))
                ViewMovieButton(isSeries, numberOfEpisodes)
            }

        }
        //when screenWidth is compact, position of View Button to the right of movie title
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            Spacer(modifier = Modifier.weight(weight = 1f))
            ViewMovieButton(isSeries, numberOfEpisodes)
        }

    }

}


@Composable
fun ViewMovieButton(isSeries: Boolean, numberOfEpisodes: Int? = 0) {
    CircularIconButton(
        hasSmallerSize = true,
        modifier = Modifier
            .size(
                dimensionResource(id = R.dimen.roundedButtonMedium),
                dimensionResource(id = R.dimen.roundedButtonMedium)
            ),
        child = {
            if (isSeries) {
                Text(
                    "$numberOfEpisodes", style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                )
            } else {
                CustomIcon(
                    icon = Icons.Default.ArrowForward,
                    iconPadding = dimensionResource(id = R.dimen.paddingSmall)
                )
            }
        },
        onClick = { /*TODO*/ }
    )
}