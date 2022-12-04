package com.davidcobbina.disneyplus.ui.components.shimmers

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.davidcobbina.disneyplus.ui.theme.black100
import com.davidcobbina.disneyplus.ui.theme.black150

@Composable
fun AnimatedShimmer(
    item: (@Composable (brush: Brush) -> Unit),
) {
    val shimmerColors = listOf(
        black150,
        black100,
        black150,
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    item(brush)
}

