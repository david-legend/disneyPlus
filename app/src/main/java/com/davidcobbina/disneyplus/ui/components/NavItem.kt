package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.theme.blackGrey50

@Composable
fun NavItem(
    painter: Painter,
    title: String,
    textStyle: TextStyle,
    contentDescription: String,
    color: Color = blackGrey50
) {
    Row() {
        Icon(painter, contentDescription = contentDescription, tint = color)
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.marginMedium)))
        Text(text = title, style = textStyle)

    }
}