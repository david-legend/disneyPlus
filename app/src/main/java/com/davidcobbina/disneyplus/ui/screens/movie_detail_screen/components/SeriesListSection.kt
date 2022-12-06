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
import com.davidcobbina.disneyplus.data.model.Episode
import com.davidcobbina.disneyplus.data.model.Genre
import com.davidcobbina.disneyplus.data.model.TvSeriesDetail
import com.davidcobbina.disneyplus.ui.components.*
import com.davidcobbina.disneyplus.util.covertMinutesToHourMinute
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun SeriesListSection(
    onHeaderClick: () -> Unit,
    detail: TvSeriesDetail?,
    episodes: List<Episode>,
    genres: List<Genre>,
    seasonNumber: String,
) {
    val paddingSpacing = dimensionResource(id = R.dimen.spacingSm)
    val genreList = mutableListOf<String>()
    for (genre in genres) {
        genreList.add(genre.name)
    }

    DisneyPlusContainer(
        title = {
            TextWithIcon(
                title = "Season $seasonNumber",
                modifier = Modifier.clickable { onHeaderClick() },
                textModifier = Modifier.padding(end = dimensionResource(id = R.dimen.paddingSmall)),
                contentDescription = stringResource(id = R.string.everything_dropdown)
            )
        },
        content = {
            Column() {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(1f),
                    mainAxisAlignment = FlowMainAxisAlignment.Center,
                    crossAxisAlignment = FlowCrossAxisAlignment.Center

//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
                ) {
                    TextListWithPunctuation(texts = genreList)
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
                        text = String.format("%.1f", detail?.voteAverage),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(paddingSpacing))

                Column {
                    FlowRow(
                        mainAxisSize = SizeMode.Expand,
                        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                        crossAxisSpacing = dimensionResource(id = R.dimen.spacingMd),
                    ) {
                        for (episode in episodes) {
                            EpisodeListItem(
                                episodeTitle = episode.name,
                                imageUrl = episode.stillPath,
                                episodeNumber = "Episode ${episode.episodeNumber}",
                                episodeDuration = covertMinutesToHourMinute(episode.runtime),
                                episodeDescription = episode.overview,
                                isDownloaded = false //TODO:: check if episode is downloaded or Not
                            )
                            Spacer(modifier = Modifier.height(paddingSpacing))
                        }
                    }

                }


            }

        }
    )
}