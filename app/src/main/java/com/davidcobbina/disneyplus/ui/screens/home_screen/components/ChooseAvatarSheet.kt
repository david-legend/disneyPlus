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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.data.local.model.AvatarCategory
import com.davidcobbina.disneyplus.data.local.model.AvatarProfile
import com.davidcobbina.disneyplus.ui.components.AvatarCategoryItem
import com.davidcobbina.disneyplus.ui.components.MovieItem

import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import kotlinx.coroutines.launch


//TODO:: Add selector UI for mascot category


@ExperimentalMaterialApi
@Composable
fun ChooseAvatarSheetContent(sheetState: BottomSheetState, avatarProfiles: List<AvatarProfile>, avatarCategories: List<AvatarCategory>) {
    val windowInfo = rememberWindowInfo()
    val screenWidth = windowInfo.screenWidth
    val screenHeight = windowInfo.screenHeight
    val bottomSheetHeight =
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) (screenHeight * 0.7).dp else screenHeight.dp
    val avatarImageSize =
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) 200.dp else 150.dp
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
            CircularIconButton(
                hasSmallerSize = true,
                buttonColor = MaterialTheme.colorScheme.primaryContainer,
                child = {
                    CustomIcon(
                        icon = Icons.Default.Close,
                        iconPadding = dimensionResource(id = R.dimen.paddingSmall),
                        contentDescription = stringResource(id = R.string.close_mascot_button_description),
                    )
                },
                onClick = {
                    scope.launch {
                        if (sheetState.isExpanded) {
                            sheetState.collapse()
                        }
                    }
                },
            )

            Spacer(modifier = Modifier.weight(0.25f))
        }

        Spacer(modifier = Modifier.weight(0.25f))
        LazyRow(
            contentPadding = PaddingValues(start = 60.dp)
        ) {

            itemsIndexed(avatarProfiles) { _, data ->
                MovieItem(
                    painter = painterResource(id = data.avatar),
                    contentDescription = stringResource(id = R.string.movie_cover_description),
                    width = avatarImageSize,
                    height = avatarImageSize,
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
        Spacer(modifier = Modifier.weight(0.15f))

    }
}

