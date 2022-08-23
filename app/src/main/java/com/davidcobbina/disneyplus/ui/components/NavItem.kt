package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.theme.grey100

@Composable
fun NavItem(
    painter: Painter,
    title: String,
    textStyle: TextStyle,
    contentDescription: String,
    iconColor: Color = grey100,
    isSelected: Boolean = false,
    selectedColor: Color = Color.White,
    dotSize: Dp = 4.dp,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter,
            contentDescription = contentDescription,
            tint = if (isSelected) selectedColor else iconColor
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.marginMedium)))
        Text(
            text = title,
            style = if (isSelected) textStyle.copy(color = selectedColor) else textStyle
        )
        if (isSelected) Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.marginSmall)))
        if (isSelected) Box(
            modifier = Modifier
                .height(dotSize)
                .width(dotSize)
                .background(
                    color = selectedColor,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.borderRadiusExtraLarge))
                ),
        )
    }
}