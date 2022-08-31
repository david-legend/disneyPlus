package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R

@Composable
fun ActionListTile(
    title: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    spacerModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.headlineSmall.copy(
        fontSize = 18.sp
    ),
    iconColor: Color = Color.White,
) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        CustomIcon(icon = icon, modifier = iconModifier, iconColor = iconColor)
        Spacer(modifier = spacerModifier.width(dimensionResource(id = R.dimen.marginXs)))
        Text(text = title, style = titleStyle, modifier = titleModifier)
    }
}