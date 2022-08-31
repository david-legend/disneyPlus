package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.davidcobbina.disneyplus.R

@Composable
fun TextWithIcon(
    title: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Filled.KeyboardArrowDown,
    textStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary


) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = textStyle,
            modifier = textModifier,
        )
        Icon(
            imageVector,
            contentDescription,
            modifier = iconModifier.size(dimensionResource(id = R.dimen.iconSizeLarge)),
            tint = iconColor
        )
    }
}