package com.davidcobbina.disneyplus.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DefaultTitle(
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
) {
    Text(text = title, style = titleStyle)
}