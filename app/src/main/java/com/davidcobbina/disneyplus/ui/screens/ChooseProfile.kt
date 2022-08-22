package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CircularImage

//TODO:: Add bouncing animations to Profile Images
@Composable
fun ChooseProfileScreen(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(
            horizontal = dimensionResource(id = R.dimen.paddingMedium),
            vertical = dimensionResource(id = R.dimen.paddingLarge)
        )
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
            CircularIconButton(
                contentDescription = stringResource(R.string.add_button_content_description),
                iconColor= Color.White,
                icon = Icons.Filled.Add,
                onClick = { /*TODO*/ },
            )
            Spacer(modifier = modifier.weight(1.0f))
            Text(text = stringResource(R.string.edit), style = MaterialTheme.typography.bodyLarge)
        }
    }
}