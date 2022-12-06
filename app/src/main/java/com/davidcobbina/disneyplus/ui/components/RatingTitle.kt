package com.davidcobbina.disneyplus.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R

@Composable
fun RatingTitle(
    title: String,
    rating: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    spacerModifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    ),
    ratingStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Normal
    ),
    contentDescription: String? = null,

    ) {
    Row(
        modifier = modifier

            .padding(horizontal = dimensionResource(id = R.dimen.spacingXXXs))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        ExpandingText(text = title, modifier = Modifier.weight(1f), textStyle = titleStyle)
        Box(modifier = spacerModifier.width(dimensionResource(id = R.dimen.paddingSmall)))
        Row() {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = imageModifier
                    .width(40.dp)
                    .height(22.dp)
                    .align(Alignment.CenterVertically)
            )
            Box(modifier = spacerModifier.width(dimensionResource(id = R.dimen.paddingSmall)))
            Text(text = rating, style = ratingStyle)
        }


    }
}


@Composable
fun ExpandingText(modifier: Modifier = Modifier, text: String, textStyle: TextStyle) {

    var showMoreEllipsis by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var shortText by remember { mutableStateOf("") }
    if (showMoreEllipsis) {
        val annotatedString = buildAnnotatedString {
            append(if (expanded) text else shortText)
            if (!expanded) {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append("... more")
                }
            }
        }
        Text(
            annotatedString,
            style = textStyle,
            modifier = modifier
                .clickable(enabled = true) {
                    expanded = !expanded
                }
                .animateContentSize())

    } else {
        DefaultTitle(title = text,
            titleStyle = textStyle,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.lineCount > 1) {
                    val lineEndIndex = textLayoutResult.getLineEnd(
                        lineIndex = 0,
                        visibleEnd = true
                    )
                    shortText = text.substring(0, lineEndIndex)
                    showMoreEllipsis = true
                }
            }
        )
    }
}