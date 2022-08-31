package com.davidcobbina.disneyplus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieDescriptionSection(
    description: String,
    genres: List<String>,
    modifier: Modifier = Modifier,
    textListModifier: Modifier = Modifier,
    spacerModifier: Modifier = Modifier,
    descriptionStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = Color.White,
        fontSize = 16.sp
    ),
    genreStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 16.sp
    )
) {
    Column(
        modifier = modifier
    ) {
        Text(text = description, style = descriptionStyle, textAlign = TextAlign.Center)
        Spacer(modifier = spacerModifier.height(16.dp))
        TextListWithPunctuation(
            texts = genres,
            textStyle = genreStyle,
            modifier = textListModifier.fillMaxWidth()
        )

    }
}