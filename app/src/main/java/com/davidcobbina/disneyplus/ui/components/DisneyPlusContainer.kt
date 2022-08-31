package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R

@Composable
fun DisneyPlusContainer(
    title: String,
    modifier: Modifier = Modifier,
    spacerModifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    ),
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    borderRadius: Dp = dimensionResource(id = R.dimen.borderRadiusExtraLarge),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    child: @Composable () -> Unit,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Column(
        modifier = modifier
            .width(screenWidth)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(borderRadius)
            )
            .padding(
                horizontal = dimensionResource(id = R.dimen.paddingExtraSmall),
                vertical = dimensionResource(id = R.dimen.paddingLarge)
            ),
        horizontalAlignment = horizontalAlignment,
    ) {
        Text(
            title, style = titleStyle
        )
        Spacer(modifier = spacerModifier.height(dimensionResource(id = R.dimen.marginExtraLarge)))
        child()
    }
}