package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.remote.api.ApiConstants
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.components.MovieItem

@Composable
fun MovieDetailHeaderSection(
    onMoreClick: () -> Unit,
    mediaType: String,
    movieCoverUrl: String,
    contentDescription: String?
) {
    val windowInfo = rememberWindowInfo()
    val screenHeight = windowInfo.screenHeight
    val headerImageWidth = windowInfo.screenWidthDp
    val headerImageHeight =
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) (screenHeight * 0.75).dp else screenHeight.dp
    val paddingSpacing = dimensionResource(id = R.dimen.spacingSm)
    Box(
        modifier = Modifier
            .width(headerImageWidth)
            .height(headerImageHeight)

    ) {
        MovieItem(
            url = movieCoverUrl, contentDescription = contentDescription,
            modifier = Modifier
                .matchParentSize()
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp)
                ),
            contentScale = ContentScale.Crop,
        )

        Row(
            modifier = Modifier
                .fillMaxHeight(1f)
                .padding(paddingSpacing),
            verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    contentPadding = PaddingValues(16.dp),
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                ) {
                    CustomIcon(
                        icon = Icons.Default.PlayArrow,
                        iconSize = 24.dp,
                        iconColor = MaterialTheme.colorScheme.primary,
                        iconPadding = 0.dp,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = if (mediaType == ApiConstants.MEDIA_TYPE_MOVIE) stringResource(id = R.string.play) else stringResource(
                            id = R.string.episode1
                        ),
                        style = MaterialTheme.typography.titleSmall
                            .copy(color = MaterialTheme.colorScheme.primary, fontSize = 16.sp)
                    )

                }
                Spacer(modifier = Modifier.width(paddingSpacing))
                CircularIconButton(
                    child = {
                        CustomIcon(
                            contentDescription = stringResource(R.string.add_movie),
                            iconColor = Color.White,
                            icon = Icons.Filled.Add,
                        )
                    },
                    buttonColor = MaterialTheme.colorScheme.onSurface,
                    onClick = { /*TODO*/ },
                )
                Spacer(modifier = Modifier.weight(1f))
                CircularIconButton(
                    child = {
                        CustomIcon(
                            contentDescription = stringResource(R.string.add_movie),
                            iconColor = Color.White,
                            icon = painterResource(id = R.drawable.ic_more_horizontal),
                        )
                    },
                    buttonColor = MaterialTheme.colorScheme.onSurface,
                    onClick = onMoreClick,
                )
            }
        }


//        Column(
//            modifier = Modifier
//                .fillMaxHeight()
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.mandalorian_title),
//                colorFilter = ColorFilter.tint(color = Color.White),
//                contentDescription = "brand"
//            )
//        }
    }
}