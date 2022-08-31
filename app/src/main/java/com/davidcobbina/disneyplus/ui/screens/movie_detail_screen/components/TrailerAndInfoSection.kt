package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.trailers
import com.davidcobbina.disneyplus.ui.components.*
import com.davidcobbina.disneyplus.ui.theme.grey600
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun TrailerAndInfoSection(
    hasMovieDescription: Boolean = false
) {
    val paddingSpacing = 16.dp
    val itemWidth: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2) - (paddingSpacing + 4.dp)
    DisneyPlusContainer(
        title = stringResource(id = R.string.trailers_and_info),
        child = {
            Column(
            ) {
                if (hasMovieDescription) {
                    MovieDescriptionSection(
                        description = stringResource(id = R.string.lorem_ipsum),
                        genres = arrayListOf("Animation", "Musical", "Action", "Comedy")
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
                }
                LazyRow(
                    contentPadding = PaddingValues(horizontal = paddingSpacing),
                    content = {
                        itemsIndexed(trailers) { _, item ->
                            Trailer(
                                painter = painterResource(id = item.movieCover)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    },
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.padding(start = paddingSpacing)
                ) {
                    FlowRow(
                        mainAxisSize = SizeMode.Expand,
                        crossAxisSpacing = paddingSpacing,
                        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
                    ) {
                        TextList(
                            title = "Director",
                            children = arrayListOf("Tony Bancroft", "Barry Cook"),
                            modifier = Modifier
                                .width(itemWidth)
                        )
                        TextList(
                            title = "Director",
                            children = arrayListOf("Tony Bancroft", "Barry Cook"),
                            modifier = Modifier
                                .width(itemWidth)
                        )
                        TextList(
                            title = "Director",
                            children = arrayListOf("Tony Bancroft", "Barry Cook"),
                            modifier = Modifier
                                .width(itemWidth)
                        )
                        Box(
                            contentAlignment = Alignment.BottomStart,
                            modifier = Modifier.width(itemWidth)
                        ) {
                            BorderedBox(
                                modifier = Modifier
                                    .width(100.dp),
                                child = {
                                    Text(
                                        text = "PG - 13",
                                        style = MaterialTheme.typography.titleSmall.copy(color = grey600)
                                    )
                                })
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        },
    )

}