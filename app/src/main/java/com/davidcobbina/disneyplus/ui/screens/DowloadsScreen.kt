package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.davidcobbina.disneyplus.data.downloadedMovies
import com.davidcobbina.disneyplus.ui.components.*


@Composable
fun DownloadScreen(navController: NavHostController) {
    val paddingSpacing = dimensionResource(id = R.dimen.spacingMd)
    Box(
        modifier = Modifier.padding(
            vertical = paddingSpacing,
            horizontal = paddingSpacing
        )
    ) {
        LazyColumn() {
            item {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.topSpacing)))
                DownloadPageTitle()
                Spacer(modifier = Modifier.height(paddingSpacing))

            }

            itemsIndexed(downloadedMovies) { _, movie ->
                DownloadedMovieItem(
                    movieCover = painterResource(id = movie.movieCover),
                    title = movie.title,
                    yearReleased = movie.yearReleased,
                    downloadedSize = movie.downloadedSize
                )
                Spacer(modifier = Modifier.height(paddingSpacing))
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
    isSeries: Boolean = false,
    numberOfEpisodes: Int? = 0,
    imageContentDescription: String? = null,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(color = Color.White),
    subtitleStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 16.sp,
    ),
) {
    val itemSize = if (isSeries) "Total of $downloadedSize" else downloadedSize
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StackedImage(
            painter = movieCover,
            contentDescription = imageContentDescription
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Column() {
            Text(title, style = titleStyle)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(yearReleased, style = subtitleStyle)
                Dot(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    dotColor = MaterialTheme.colorScheme.onPrimary,
                    dotSize = 2.dp
                )
                Text(itemSize, style = subtitleStyle)
            }
        }
        Spacer(modifier = Modifier.weight(weight = 1f))
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
}