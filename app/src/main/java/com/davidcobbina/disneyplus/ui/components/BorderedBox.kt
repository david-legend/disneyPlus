package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.theme.grey600

@Composable
fun BorderedBox(
    child: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = grey600,
    borderWidth: Dp = 1.dp,
    borderRadius: Dp = dimensionResource(id = R.dimen.borderRadiusExtraSmall),
) {
    Box(
        modifier = modifier
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(borderRadius)
            )
            .padding(
                horizontal = dimensionResource(id = R.dimen.paddingMedium),
                vertical = dimensionResource(id = R.dimen.paddingSmall)
            )
    ) {
        child()
    }
}