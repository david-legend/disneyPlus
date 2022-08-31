package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TextList(
    title: String,
    children: List<String>,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.bodyLarge
        .copy(color = MaterialTheme.colorScheme.onPrimary),
    childrenStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
    maxChildren: Int = 4
) {
    val length = if (children.size > maxChildren) maxChildren - 1 else children.size - 1
    val hasShowMore = children.size > maxChildren
    Column(
        modifier = modifier
    ) {
        Text(title, style = titleStyle)
        for (index in 0..length) {
            Text(children[index], style = childrenStyle)
        }
        if (hasShowMore) {
            Text("More")
        }
    }
}