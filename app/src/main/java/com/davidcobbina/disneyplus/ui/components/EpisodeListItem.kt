package com.davidcobbina.disneyplus.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.ui.theme.black400
import com.davidcobbina.disneyplus.ui.theme.green50
import com.davidcobbina.disneyplus.ui.theme.lightGreen

const val EXPANSION_TRANSITION_DURATION = 450
const val EXPANSION_TRANSITION_DURATIONS = 4500

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun EpisodeListItem(
    imageUrl: String?,
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
    val windowInfo = rememberWindowInfo()
    val paddingLeft = dimensionResource(id = R.dimen.paddingExtraMedium)
    val paddingRight = dimensionResource(id = R.dimen.paddingSmall)
    val screenWidth = windowInfo.screenWidth - (paddingLeft.value + paddingRight.value)
    val itemWidth =
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) screenWidth else (screenWidth * 0.5)
    val episodePreviewWidth = (itemWidth.toInt() * 0.5)
    val episodePreviewHeight = (episodePreviewWidth * 0.7)

    var expanded by remember { mutableStateOf(false) }
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "transition")
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = EXPANSION_TRANSITION_DURATION)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }
    val height by transition.animateDp({
        tween(durationMillis = EXPANSION_TRANSITION_DURATION)
    }, label = "heightTransition") {
        if (expanded) (episodePreviewHeight + 60).dp else episodePreviewHeight.dp
    }
    Column(
        modifier = Modifier
            .width(itemWidth.toInt().dp)
            .height(height)
            .padding(
                start = paddingLeft,
                end = paddingRight
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(episodePreviewHeight.dp),
        ) {
            MoviePreview(
                imageUrl = imageUrl,
                contentDescription = episodeTitle,
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
                    modifier = Modifier.clickable(enabled = true) {
                        expanded = !expanded
                    },
                    textModifier = Modifier.weight(1f),
                    iconModifier = Modifier
                        .size(dimensionResource(id = R.dimen.iconSizeMedium))
                        .rotate(arrowRotationDegree)
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
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
        if (expanded) {
            ExpandableContent(text = episodeDescription, textStyle = episodeDescriptionStyle)
//            Row {
//                Text(text = episodeDescription, style = episodeDescriptionStyle)
//            }
        }

    }
}

@Composable
fun ExpandableContent(
    text: String,
    visible: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = Color.White,
        fontSize = 16.sp
    )
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPANSION_TRANSITION_DURATION)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(EXPANSION_TRANSITION_DURATION)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(EXPANSION_TRANSITION_DURATION)
        ) + fadeOut(
            // Fade in with the initial alpha of 0.3f.
            animationSpec = tween(EXPANSION_TRANSITION_DURATION)
        )
    }

    AnimatedVisibility(
        visible = visible,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Row {
            Text(text = text, style = textStyle)
        }
    }
}