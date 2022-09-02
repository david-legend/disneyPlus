package com.davidcobbina.disneyplus.ui.screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.TextWithIcon


@Composable
fun HeaderSection(navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.paddingLarge))
    ) {
        TextWithIcon(
            title = stringResource(id = R.string.everything),
            contentDescription = stringResource(id = R.string.everything_dropdown),
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.MenuScreen.route)
            }
        )
        Spacer(modifier = Modifier.weight(1.0f))
        IconButton(
            onClick = { /*TODO*/ }, Modifier.background(
                color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.borderRadiusExtraLarge)
                )
            )
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_movie_icon),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.iconSizeMedium)),
                tint = MaterialTheme.colorScheme.onSecondary,
            )
        }
        Box(modifier = Modifier.width(dimensionResource(id = R.dimen.spacingSm)))
        IconButton(
            onClick = {
                navController.navigate(Screen.DownloadsScreen.route)
            },
            Modifier.background(
                color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.borderRadiusExtraLarge),
                )
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_downward),
                contentDescription = stringResource(id = R.string.download_icon),
                modifier = Modifier.size(dimensionResource(id = R.dimen.iconSizeMedium)),
                tint = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}