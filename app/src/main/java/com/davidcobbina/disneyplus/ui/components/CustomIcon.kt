package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.davidcobbina.disneyplus.R

@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    icon: ImageVector,
    iconSize: Dp = dimensionResource(id = R.dimen.iconSizeMedium),
    iconPadding: Dp = dimensionResource(id = R.dimen.paddingMedium),
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier
            .padding(iconPadding)
            .size(iconSize),
        tint = iconColor
    )
}

@Composable
fun CustomIcon(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    icon: Painter,
    iconSize: Dp = dimensionResource(id = R.dimen.iconSizeMedium),
    iconPadding: Dp = dimensionResource(id = R.dimen.paddingMedium),
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    Icon(
        painter = icon,
        contentDescription = contentDescription,
        modifier = modifier
            .padding(iconPadding)
            .size(iconSize),
        tint = iconColor
    )
}