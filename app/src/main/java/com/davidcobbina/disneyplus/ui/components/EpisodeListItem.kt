package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.theme.black400
import com.davidcobbina.disneyplus.ui.theme.green50
import com.davidcobbina.disneyplus.ui.theme.lightGreen


@Composable
fun EpisodeListItem(
    episodeTitle: String,
    episodeNumber: String,
    episodeDuration: String,
    episodeDescription: String,
    episodeTitleStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = Color.White,
        fontSize = 16.sp
    ),
    episodeNumberStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
    episodeDurationStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
    episodeDescriptionStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 15.sp,
    ),
    isDownloaded: Boolean = false
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val episodePreviewWidth = (screenWidth * 0.5)
    val episodePreviewHeight = (episodePreviewWidth * 0.7)
    Column(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.paddingExtraMedium),
            end = dimensionResource(id = R.dimen.paddingSmall)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(episodePreviewHeight.dp),
        ) {
            MoviePreview(
                painterResource(id = R.drawable.mandalorian_sunny),
                modifier = Modifier
                    .height(episodePreviewHeight.dp)
                    .width(episodePreviewWidth.dp)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.paddingExtraMedium)))
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(episodeNumber, style = episodeNumberStyle)
                    Dot(dotSize = 2.dp, dotColor = MaterialTheme.colorScheme.onPrimary)
                    Text(episodeDuration, style = episodeDurationStyle)
                }
                TextWithIcon(
                    title = episodeTitle,
                    textStyle = episodeTitleStyle,
                    contentDescription = episodeTitle,
                    iconModifier = Modifier.size(dimensionResource(id = R.dimen.iconSizeMedium))
                )
                Spacer(modifier = Modifier.weight(1f))
                CircularIconButton(
                    hasSmallerSize = true,
                    buttonColor = if (isDownloaded) green50 else black400,
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(44.dp),
                    child = {
                        CustomIcon(
                            icon = painterResource(
                                id = if (isDownloaded) R.drawable.ic_smartphone else R.drawable.ic_arrow_down
                            ),
                            iconColor = if (isDownloaded) lightGreen else Color.White,
                            iconPadding = dimensionResource(id = R.dimen.paddingExtraSmall),
                        )
                    },
                )
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
        Row(

        ) {
            Text(text = episodeDescription, style = episodeDescriptionStyle)
        }
    }
}