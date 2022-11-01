package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.background
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
fun DefaultEditableIcon() {
    CustomIcon(
        icon = painterResource(id = R.drawable.ic_outline_edit),
        iconPadding = 0.dp,
        iconColor = Color.White,
        iconSize = iconSize
    )
}

@Composable
fun EditableItem(
    child: @Composable () -> Unit,
    isEditable: Boolean = false,
    childHeight: Dp = 100.dp, //used for computing and centering the editable Icon
    editableIconHeight: Dp = iconSize, //used for computing and centering the editable Icon
    editableIcon: @Composable () -> Unit = { DefaultEditableIcon() },
) {
    val padding = (childHeight - editableIconHeight) / 2
    Box(
        contentAlignment = Alignment.TopCenter,
    ) {
        child()
        if (isEditable) {
            Box(modifier = Modifier.padding(top = padding)) {
                editableIcon()
            }
        }
    }
}