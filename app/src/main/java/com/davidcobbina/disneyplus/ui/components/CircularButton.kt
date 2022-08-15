package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.davidcobbina.disneyplus.R

@Composable
fun CircularIconButton(
    contentDescription: String?,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.background(
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.borderRadiusExtraLarge))
    ),
    iconModifier: Modifier = Modifier
        .padding(dimensionResource(id = R.dimen.paddingMedium))
        .size(dimensionResource(id = R.dimen.iconSizeMedium)),
    iconColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = iconModifier,
            tint = iconColor
        )
    }
}

@Composable
fun CircularIconButton(
    contentDescription: String?,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.background(
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.borderRadiusExtraLarge))
    ),
    iconModifier: Modifier = Modifier
        .padding(dimensionResource(id = R.dimen.paddingMedium))
        .size(dimensionResource(id = R.dimen.iconSizeMedium)),
    iconColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            modifier = iconModifier,
            tint = iconColor
        )
    }
}