package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.components.*

@Composable
fun SeriesListSection(
    onHeaderClick: () -> Unit
) {
    val paddingSpacing = dimensionResource(id = R.dimen.spacingSm)
    DisneyPlusContainer(
        title = {
            TextWithIcon(
                title = "Season 2",
                modifier = Modifier.clickable { onHeaderClick() },
                textModifier = Modifier.padding(end = dimensionResource(id = R.dimen.paddingSmall)),
                contentDescription = stringResource(id = R.string.everything_dropdown)
            )
        },
        content = {
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextListWithPunctuation(
                        texts = arrayListOf(
                            "Space Western",
                            "Action",
                            "Adventure"
                        )
                    )
                    Dot(
                        dotColor = MaterialTheme.colorScheme.onPrimary,
                        dotSize = 2.dp,
                        padding = dimensionResource(id = R.dimen.paddingSmall)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.imdb),
                        contentDescription = stringResource(id = R.string.imdb_image),
                        modifier = Modifier
                            .width(40.dp)
                            .height(20.dp)
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.paddingExtraSmall)))
                    Text(
                        text = "8.9",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(paddingSpacing))
                EpisodeListItem(
                    episodeTitle = "The Mandalorian",
                    episodeNumber = "Episode 1",
                    episodeDuration = "39m",
                    episodeDescription = stringResource(id = R.string.lorem_ipsum),
//                    isDownloaded = true
                )
            }

        }
    )
}