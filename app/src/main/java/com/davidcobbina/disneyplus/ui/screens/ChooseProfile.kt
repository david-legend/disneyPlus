package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.components.CircularImage
import com.davidcobbina.disneyplus.ui.theme.blackGrey100

//TODO:: Add bouncing animations to Profile Images

@Composable
fun ChooseProfileScreen(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.who_is_watching),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = modifier.weight(1.0f))
        CircularImage(
            painter = painterResource(R.drawable.jasmine),
            contentDescription = stringResource(R.string.profile_content_description),
            imageTitle = stringResource(R.string.megan)
        )
        Spacer(modifier = modifier.weight(0.4f))
        CircularImage(
            painter = painterResource(R.drawable.scar),
            contentDescription = stringResource(R.string.profile_content_description),
            imageTitle = stringResource(R.string.anthony)
        )
        Spacer(modifier = modifier.weight(0.4f))
        CircularImage(
            painter = painterResource(R.drawable.mushu),
            contentDescription = stringResource(R.string.profile_content_description),
            imageTitle = stringResource(R.string.marc)
        )
        Spacer(modifier = modifier.weight(1.0f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.background(
                    color = blackGrey100,
                    shape = RoundedCornerShape(24.dp)
                )
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_button_content_description),
                    tint = Color.White
                )
            }
            Spacer(modifier = modifier.weight(1.0f))
            Text(text = stringResource(R.string.edit), style = MaterialTheme.typography.bodyLarge)
        }
    }
}