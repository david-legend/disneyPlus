package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.components.CircularImage

@Composable
fun ChooseProfileScreen(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(text = "Who's watching")
        Spacer(modifier = modifier.weight(1.0f))
        CircularImage(
            painter = painterResource(R.drawable.jasmine),
            contentDescription = "Jasmine",
            imageTitle = "Megan"
        )
        Spacer(modifier = modifier.weight(0.4f))
        CircularImage(
            painter = painterResource(R.drawable.scar),
            contentDescription = "Scar",
            imageTitle = "Anthony"
        )
        Spacer(modifier = modifier.weight(0.4f))
        CircularImage(
            painter = painterResource(R.drawable.mushu),
            contentDescription = "Mushu",
            imageTitle = "Marc"
        )
        Spacer(modifier = modifier.weight(1.0f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add Profile",
                    tint = Color.Red
                )
            }
            Spacer(modifier = modifier.weight(1.0f))
            Text(text = "Edit")
        }
    }
}