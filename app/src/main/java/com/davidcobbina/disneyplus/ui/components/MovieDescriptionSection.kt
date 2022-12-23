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
import com.davidcobbina.disneyplus.data.remote.model.Genre

class TextData
@Composable
fun MovieDescriptionSection(
    description: String,
    genres: List<Genre>,
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
    val textList  = mutableListOf<String>()
    for (genre in genres) {
        textList.add(genre.name)
    }

    Column(
        modifier = modifier
    ) {
        Text(text = description, style = descriptionStyle, textAlign = TextAlign.Center)
        Spacer(modifier = spacerModifier.height(16.dp))
        TextListWithPunctuation(
            texts = textList,
            textStyle = genreStyle,
            modifier = textListModifier.fillMaxWidth()
        )

    }
}