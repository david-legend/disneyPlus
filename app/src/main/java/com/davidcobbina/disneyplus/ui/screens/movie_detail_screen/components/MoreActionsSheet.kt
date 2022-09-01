package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.components

import androidx.compose.foundation.layout.*
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
import com.davidcobbina.disneyplus.data.actionsList
import com.davidcobbina.disneyplus.ui.components.ActionListTile
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import kotlinx.coroutines.launch


@Composable
@ExperimentalMaterialApi
fun MoreActionsSheet(sheetState: BottomSheetState, title: String) {
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
                bottom = dimensionResource(id = R.dimen.paddingLarge),
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1.0f))
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
            Spacer(modifier = Modifier.weight(0.7f))
            CircularIconButton(
                buttonColor = MaterialTheme.colorScheme.primaryContainer,
                hasSmallerSize = true,
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
        Column() {
            for (action in actionsList) {
                ActionListTile(title = action.title, icon = painterResource(id = action.icon))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.paddingMedium)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CircularIconButton(
                buttonColor = MaterialTheme.colorScheme.primaryContainer,
                child = {
                    CustomIcon(icon = painterResource(id = R.drawable.ic_heart))
                },
                onClick = { /*TODO*/ },
            )
            CircularIconButton(
                buttonColor = MaterialTheme.colorScheme.primaryContainer,
                child = {
                    CustomIcon(icon = painterResource(id = R.drawable.ic_thumbs_down))
                },
                onClick = { /*TODO*/ },
            )
        }
    }
}