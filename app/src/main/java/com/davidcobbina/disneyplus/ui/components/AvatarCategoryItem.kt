package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R

@Composable
fun AvatarCategoryItem(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean = false,
    inactiveBackgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    activeBackgroundColor: Color = MaterialTheme.colorScheme.onSurface,
    activeBorderRadius: Dp = dimensionResource(id = R.dimen.borderRadiusSmall),
    inactiveBorderRadius: Dp = dimensionResource(id = R.dimen.borderRadiusNone),
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
) {
    val background = if (isSelected) activeBackgroundColor else inactiveBackgroundColor
    val borderRadius = if (isSelected) activeBorderRadius else inactiveBorderRadius
    Box(
        modifier = modifier
            .background(color = background, shape = RoundedCornerShape(borderRadius))
            .padding(vertical = 10.dp, horizontal = 6.dp),
        contentAlignment=  Alignment.Center,

    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = textStyle.copy(
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = if (isSelected) FontWeight.W600 else FontWeight.W400
            )
        )
    }
}