package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.ui.theme.red

@Composable
fun DisneyButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = red,
    colors: ButtonColors = ButtonDefaults.textButtonColors(
        backgroundColor = backgroundColor
    ),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    buttonHeight: Dp = 48.dp,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(buttonHeight),
        colors = colors,
        content = content,
        contentPadding = contentPadding,
    )
}