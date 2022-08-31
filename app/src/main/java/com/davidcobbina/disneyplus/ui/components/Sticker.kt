package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.davidcobbina.disneyplus.R

@Composable
fun Sticker(
    child: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
    borderRadius: Dp = dimensionResource(id = R.dimen.borderRadiusExtraSmall)
) {
    Box(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(borderRadius))
            .padding(
                horizontal = dimensionResource(id = R.dimen.paddingSmall),
                vertical = dimensionResource(id = R.dimen.paddingExtraSmall)
            )
    ) {
        child()
    }
}