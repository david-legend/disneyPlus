package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.theme.black100


// For buttons that takes in an ImageVector as an Icon
/// Also buttons with sizes equal to or above 48dp * 48dp
@Composable
fun CircularIconButton(
    contentDescription: String?,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    buttonColor: Color = MaterialTheme.colorScheme.secondary,
    icon: ImageVector,
    iconSize: Dp = dimensionResource(id = R.dimen.iconSizeMedium),
    iconPadding: Dp = dimensionResource(id = R.dimen.paddingMedium),
    onClick: () -> Unit,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.borderRadiusExtraLarge))
            ),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = iconModifier
//                .size(width = 16.dp, height = 16.dp)
                .padding(iconPadding)
                .size(iconSize),
            tint = iconColor
        )
    }
}

// For buttons that takes in a painter as an Icon
/// Also buttons with sizes equal to or above 48dp * 48dp
@Composable
fun CircularIconButton(
    contentDescription: String?,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.borderRadiusExtraLarge))
        ),
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            modifier = iconModifier
                .padding(dimensionResource(id = R.dimen.paddingMedium))
                .size(dimensionResource(id = R.dimen.iconSizeMedium)),
            tint = iconColor
        )
    }
}

// Custom circular button, allows creation of buttons lesser than 48dps
@Composable
fun CircularButton(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    buttonColor: Color = black100,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Box(
        modifier = modifier
            .size(40.dp, 40.dp)
            .background(color = buttonColor, shape = RoundedCornerShape(24.dp))
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.matchParentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                tint = iconColor,
                contentDescription = contentDescription,
                modifier = iconModifier
                    .size(24.dp, 24.dp)
            )
        }
    }
}