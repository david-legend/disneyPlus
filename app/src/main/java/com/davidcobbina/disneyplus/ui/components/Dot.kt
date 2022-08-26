package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R

@Composable
fun Dot(
    modifier: Modifier = Modifier,
    dotColor: Color = Color.White,
    dotSize: Dp = 4.dp
) {
    Box(
        modifier = modifier
            .height(dotSize)
            .width(dotSize)
            .background(
                color = dotColor,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.borderRadiusMedium))
            ),
    )
}