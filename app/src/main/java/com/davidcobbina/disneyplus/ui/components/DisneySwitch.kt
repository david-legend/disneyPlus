package com.davidcobbina.disneyplus.ui.components

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.davidcobbina.disneyplus.ui.theme.blue300

@Composable
fun DisneySwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Switch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            uncheckedThumbColor = Color.White,
            checkedTrackColor = blue300,
            uncheckedTrackColor = Color.DarkGray,
            checkedTrackAlpha = 1.0f,
            uncheckedTrackAlpha = 1.0f
        ),
    )
}