package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.ui.components.*


@Composable
fun DownloadScreen() {
    Box(
        modifier = Modifier.background(color = Color.White)
    ) {
        LazyColumn() {
            item {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.topSpacing)))
                DownloadPageTitle()
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
                Row() {
                    StackedImage(
                        painter = painterResource(id = R.drawable.mandalorian),
                        contentDescription = ""
                    )
                }
            }


        }
        CircularIconButton(
            child = {
                CustomIcon(
                    contentDescription = stringResource(R.string.add_button_content_description),
                    iconColor = Color.White,
                    icon = Icons.Filled.Close,
                )
            },
            buttonColor = MaterialTheme.colorScheme.surface,
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    horizontal = dimensionResource(id = R.dimen.paddingLarge),
                    vertical = dimensionResource(id = R.dimen.paddingLarge)
                )
                .clickable {
                    //pop current screen off
                }
        )
    }

}


@Composable
fun DownloadPageTitle() {
    Column {
        HeaderTextWithIcon(
            title = stringResource(id = R.string.downloads),
            contentDescription = stringResource(id = R.string.downloads_dropdown)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginExtraMedium)))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "24 Videos",
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
            )
            Dot(
                modifier = Modifier.padding(horizontal = 4.dp),
                dotColor = MaterialTheme.colorScheme.onPrimary,
                dotSize = 2.dp
            )
            Text(
                text = "7.15GB",
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}

@Composable
fun DownloadedMovieItem() {
    Row() {
        StackedImage(
            painter = painterResource(id = R.drawable.mandalorian),
            contentDescription = ""
        )
        Column() {
            Text("The mandalorian")
            Row() {
                Text("2019")
                Dot(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    dotColor = MaterialTheme.colorScheme.onPrimary,
                    dotSize = 2.dp
                )
                Text("Total od 902.7mb")
            }
        }

    }
}