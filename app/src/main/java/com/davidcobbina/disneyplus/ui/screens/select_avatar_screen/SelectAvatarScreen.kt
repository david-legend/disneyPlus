package com.davidcobbina.disneyplus.ui.screens.select_avatar_screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.local.model.AvatarProfile
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.navigation.SELECT_AVATAR_ARGUMENT
import com.davidcobbina.disneyplus.ui.components.*
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@Composable
fun SelectAvatarScreen(
    navController: NavController,
    viewModel: SelectAvatarViewModel = hiltViewModel()
) {
    val selectedIcon by viewModel.selectedIcon.collectAsState()
    val avatars by viewModel.avatars.collectAsState()
    val verticalSpacing = dimensionResource(id = R.dimen.paddingMedium)
    val context = LocalContext.current
    LaunchedEffect(key1 = context ) {
        viewModel.selectAvatarEvent.collect { event ->
            when (event) {
                is SelectAvatarViewModel.SelectAvatarEvent.NavigateToAddEditProfile -> {
                    val selectedAvatar = event.selectedIcon ?: viewModel.previouslySelectedAvatar
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(SELECT_AVATAR_ARGUMENT, selectedAvatar)
                    navController.popBackStack()
                }
                is SelectAvatarViewModel.SelectAvatarEvent.SelectAvatar -> {
                    viewModel.updateAvatarMap(event.avatar)
                }
            }
        }
    }

    Box {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            //App Bar
            item {
                SelectAvatarAppBar(onCloseSelectAvatar = {
                    viewModel.onNavigateToAddEditProfile(selectedIcon)
                }, isIconSelected = selectedIcon != null)
            }
            // Icons List
            item {
                for (avatar in avatars) {
                    AvatarList(title = avatar.key, avatars = avatar.value, onAvatarSelect = { id ->
                        viewModel.onAvatarSelected(id)
                    })
                    Spacer(modifier = Modifier.height(verticalSpacing))
                }
            }
        }
    }
}


@Composable
fun SelectAvatarAppBar(onCloseSelectAvatar: () -> Unit, isIconSelected: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.paddingSmall),
                horizontal = dimensionResource(id = R.dimen.paddingSmall)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.choose_icon),
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        CustomIcon(
            modifier = Modifier.clickable { onCloseSelectAvatar() },
            contentDescription = stringResource(R.string.add_button_content_description),
            iconColor = Color.White,
            icon = if (isIconSelected) Icons.Filled.Check else Icons.Filled.Close,
        )
    }
}

@Composable
fun AvatarList(
    title: String, avatars: List<AvatarProfile>,
    onAvatarSelect: (Int) -> Unit
) {
    val windowInfo = rememberWindowInfo()
    val horizontalScreenSpacing = dimensionResource(id = R.dimen.paddingMedium)
    val screenVerticalSpacing = dimensionResource(id = R.dimen.paddingMedium)
    val screenWidthWithoutPadding = windowInfo.screenWidthDp - (horizontalScreenSpacing * 2)
    TextWithIcon(
        title = title,
        textStyle = MaterialTheme.typography.titleMedium.copy(
            color = Color.White,
            fontWeight = FontWeight.W400,
        ),
        hasIcon = false,
        modifier = Modifier.padding(start = horizontalScreenSpacing)
    )
    Spacer(modifier = Modifier.height(screenVerticalSpacing))
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        val itemWidth = screenWidthWithoutPadding / 3
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            crossAxisSpacing = screenVerticalSpacing
        ) {
            for (avatar in avatars) {
                SelectableItem(
                    selectedIcon = { DefaultSelectableIcon(icon = R.drawable.ic_check) },
                    child = {
                        CircularImage(
                            modifier = Modifier
                                .width(itemWidth)
                                .clickable {
                                    Log.i("ADD", "ON TAP OF AVATAR ${avatar.avatar}")
                                    onAvatarSelect(avatar.avatar) },
                            painter = painterResource(avatar.avatar),
                            contentDescription = stringResource(R.string.profile_content_description),
                            hasTitle = false,
                        )
                    },
                    isSelected = avatar.isSelected
                )
            }
        }

    } else {
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            crossAxisSpacing = screenVerticalSpacing
        ) {
            val itemWidth = screenWidthWithoutPadding / 3
            for (avatar in avatars) {
                SelectableItem(
                    selectedIcon = { DefaultSelectableIcon(icon = R.drawable.ic_check) },
                    child = {
                        CircularImage(
                            modifier = Modifier
                                .width(itemWidth)
                                .clickable { onAvatarSelect(avatar.avatar) },
                            painter = painterResource(avatar.avatar),
                            contentDescription = stringResource(R.string.profile_content_description),
                            hasTitle = false,
                        )
                    },
                    isSelected = avatar.isSelected
                )
            }
        }
    }
}