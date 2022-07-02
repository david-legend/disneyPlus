package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.components.MovieItem

@Composable
fun HomeScreen() {
    Column(
        Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                Text(text = "Everything")
                Icon(
                    Icons.Filled.KeyboardArrowDown, contentDescription = "Everything",
                    tint = Color.Red
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Search")
            }
        }
        Row(

        ) {
            MovieItem(
                painter = painterResource(id = R.drawable.mandalorian),
                contentDescription = "mandalorian"
            )

        }
    }
}