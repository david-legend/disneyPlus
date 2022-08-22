package com.davidcobbina.disneyplus.ui.screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.avatarList
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.MovieItem

import com.davidcobbina.disneyplus.ui.components.CircularButton
import com.davidcobbina.disneyplus.ui.theme.black100
import com.davidcobbina.disneyplus.ui.theme.black300
import kotlinx.coroutines.launch


//TODO:: Add selector UI for mascot category
//TODO:: Add select state
//TODO:: Add more images for either categories


@ExperimentalMaterialApi
@Composable
fun ChooseAvatarSheetContent(sheetState: BottomSheetState) {
    val bottomSheetHeight = (LocalConfiguration.current.screenHeightDp * 0.7).dp
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .height(bottomSheetHeight)
            .padding(
                top = dimensionResource(id = R.dimen.paddingExtraLarge),
                bottom = dimensionResource(id = R.dimen.paddingExtraLarge)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = stringResource(id = R.string.choose_mascot),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
            )
            Spacer(modifier = Modifier.weight(0.7f))
            CircularButton(
                icon = Icons.Default.Close,
                onClick = {
                    scope.launch {
                        if (sheetState.isExpanded) {
                            sheetState.collapse()
                        }
                    }
                },
                contentDescription = stringResource(id = R.string.close_mascot_button_description),
            )

            Spacer(modifier = Modifier.weight(0.25f))
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginExtraLarge)))

        LazyRow(
            contentPadding = PaddingValues(start = 60.dp)
        ) {

            itemsIndexed(avatarList) { _, data ->
                MovieItem(
                    painter = painterResource(id = data.avatar),
                    contentDescription = stringResource(id = R.string.movie_cover_description),
                    width = 200.dp,
                    height = 200.dp,
                    borderRadius = 150.dp,
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.paddingMedium))
                )
            }
        }
    }
}