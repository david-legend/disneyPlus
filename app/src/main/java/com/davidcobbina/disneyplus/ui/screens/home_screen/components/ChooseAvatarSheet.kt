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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.avatarCategories
import com.davidcobbina.disneyplus.data.avatarProfilesList
import com.davidcobbina.disneyplus.ui.components.AvatarCategoryItem
import com.davidcobbina.disneyplus.ui.components.MovieItem

import com.davidcobbina.disneyplus.ui.components.CircularButton
import kotlinx.coroutines.launch


//TODO:: Add selector UI for mascot category


@ExperimentalMaterialApi
@Composable
fun ChooseAvatarSheetContent(sheetState: BottomSheetState) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val bottomSheetHeight = (LocalConfiguration.current.screenHeightDp * 0.7).dp
    val avatarSelectorPadding = screenWidth * 0.1
    val avatarCategoryItemWidth = screenWidth - (avatarSelectorPadding * 2)
    val selectorBorderRadius = dimensionResource(id = R.dimen.borderRadiusSmall)
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .height(bottomSheetHeight)
            .padding(
                top = dimensionResource(id = R.dimen.paddingExtraLarge),
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

        Spacer(modifier = Modifier.weight(0.3f))
        LazyRow(
            contentPadding = PaddingValues(start = 60.dp)
        ) {

            itemsIndexed(avatarProfilesList) { _, data ->
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

        Spacer(modifier = Modifier.weight(0.6f))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(selectorBorderRadius)
                    )
            ) {
                val itemWidth = avatarCategoryItemWidth / avatarCategories.size
                avatarCategories.forEach { category ->
                    AvatarCategoryItem(
                        title = stringResource(id = category.title),
                        isSelected = category.isSelected,
                        modifier = Modifier.width(itemWidth.dp),
                        activeBorderRadius = selectorBorderRadius,
                        inactiveBackgroundColor = Color.Transparent,
                    )
                }
            }

        }
        Spacer(modifier = Modifier.weight(0.1f))

    }
}

