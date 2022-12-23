package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import com.davidcobbina.disneyplus.R

val iconSize: Dp = 36.dp

@Composable
fun DefaultSelectableIcon(icon: Int  = R.drawable.ic_outline_edit) {
    CustomIcon(
        icon = painterResource(id = icon),
        iconPadding = 0.dp,
        iconColor = Color.White,
        iconSize = iconSize
    )
}

@Composable
fun SelectableItem(
    child: @Composable () -> Unit,
    isSelected: Boolean = false,
    childHeight: Dp = 100.dp, //used for computing and centering the editable Icon
    editableIconHeight: Dp = iconSize, //used for computing and centering the editable Icon
    selectedIcon: @Composable () -> Unit = { DefaultSelectableIcon() },
) {
    val padding = (childHeight - editableIconHeight) / 2
    Box(
        contentAlignment = Alignment.TopCenter,
    ) {
        child()
        if (isSelected) {
            Box(modifier = Modifier.padding(top = padding)) {
                selectedIcon()
            }
        }
    }
}