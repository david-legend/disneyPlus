package com.davidcobbina.disneyplus.ui.screens.add_edit_user_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.local.model.UserProfile
import com.davidcobbina.disneyplus.navigation.SELECT_AVATAR_ARGUMENT
import com.davidcobbina.disneyplus.ui.components.*
import com.davidcobbina.disneyplus.navigation.Screen

@Composable
fun AddEditUserScreen(
    navController: NavHostController,
    viewModel: AddEditUserViewModel = hiltViewModel()
) {
    val isAdd = viewModel.isAdd
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val autoPlayEpisodeState = remember { mutableStateOf(true) }
    val autoPlayPreviewsState = remember { mutableStateOf(true) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val userName = viewModel.userName.value
    val profile by viewModel.userProfile.collectAsState()
    val addProfileSuccessMessage = stringResource(id = R.string.add_profile_success)
    val editProfileSuccessMessage = stringResource(id = R.string.edit_profile_success)
    val userNameInvalidMessage = stringResource(id = R.string.username_invalid)


    // get selected Icon from Select Avatar Screen
    navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(SELECT_AVATAR_ARGUMENT)
        ?.observe(
            lifecycleOwner
        ) { result ->
            viewModel.onAvatarSelected(result)
        }

    LaunchedEffect(key1 = context) {
        viewModel.addEditUserEvent.collect { event ->
            when (event) {
                is AddEditUserViewModel.AddEditUserEvent.NavigateToSelectAvatarScreen -> {
                    navController.navigate(
                        route = Screen.SelectAvatarScreen.passAvatar(
                            profile.avatar
                        )
                    )
                }
                is AddEditUserViewModel.AddEditUserEvent.SaveUserProfile -> {
                    viewModel.saveProfile(profile)
                }
                is AddEditUserViewModel.AddEditUserEvent.NavigateToSelectAccountScreen -> {
                    navController.popBackStack()
                }
                is AddEditUserViewModel.AddEditUserEvent.UserNameChanged -> {
                    viewModel.updateUserName(event.name)
                }
                is AddEditUserViewModel.AddEditUserEvent.UpdateSelectedAvatar -> {
                    viewModel.updateSelectedAvatar(event.avatar)
                }
                is AddEditUserViewModel.AddEditUserEvent.InValidForm -> {
                    DisneyToast(context, userNameInvalidMessage)
                }
                is AddEditUserViewModel.AddEditUserEvent.SavedProfileSuccess -> {
                    val message =
                        if (viewModel.isAdd) addProfileSuccessMessage else editProfileSuccessMessage
                    DisneyToast(context, message)
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                focusManager.clearFocus()
            }
            .padding(16.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AddEditUserAppBar(
                    isAdd,
                    onCancel = { viewModel.onNavigateToSelectAccountScreen() },
                    onSaveUserProfile = { viewModel.onSaveUserProfile() }
                )
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                EditProfilePhoto(onEditAvatar = {
                    viewModel.onNavigateToSelectAvatarScreen()
                }, profile)
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                AddEditUserName(focusRequester,
                    isAdd = isAdd,
                    username = userName,
                    onUserNameChanged = {
                        viewModel.onUserNameChange(it)
                    }
                )
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingXXl)))
            }

            itemsIndexed(viewModel.data.settingsList) { _, settings ->
                DefaultSettingsTile(
                    title = stringResource(id = settings.title),
                    subtitle = stringResource(id = settings.subtitle),
                    leadingIcon = painterResource(id = settings.leadingIcon),
                    trailingIcon = painterResource(id = settings.trailingIcon)
                )
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginXs)))
            }
            item {
                DefaultSettingsTile(
                    title = stringResource(id = R.string.autoplay_next_episode_title),
                    subtitle = stringResource(id = R.string.autoplay_next_episode_subtitle),
                    leadingIcon = painterResource(id = R.drawable.ic_baseline_queue_play_next),
                    trailing = {
                        DisneySwitch(
                            checked = autoPlayEpisodeState.value,
                            onCheckedChange = { autoPlayEpisodeState.value = it },
                        )
                    }
                )
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginXs)))
                DefaultSettingsTile(
                    title = stringResource(id = R.string.autoplay_previews_title),
                    subtitle = stringResource(id = R.string.autoplay_previews_subtitle),
                    leadingIcon = painterResource(id = R.drawable.ic_repeat),
                    trailing = {
                        DisneySwitch(
                            checked = autoPlayPreviewsState.value,
                            onCheckedChange = { autoPlayPreviewsState.value = it },
                        )
                    }
                )

                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingSm)))

                Text(
                    stringResource(id = R.string.changes),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun AddEditUserAppBar(
    isAdd: Boolean,
    onCancel: () -> Unit,
    onSaveUserProfile: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(onClick = { onCancel() }) {
            Text(
                text = stringResource(id = R.string.cancel),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        }

        Text(
            text = if (isAdd) stringResource(id = R.string.add_profile) else stringResource(id = R.string.edit_profile),
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        )
        TextButton(onClick = { onSaveUserProfile() }) {
            Text(
                text = stringResource(id = R.string.done),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        }

    }
}

@Composable
fun EditProfilePhoto(onEditAvatar: () -> Unit, profilePhoto: UserProfile) {
    SelectableItem(
        isSelected = true,
        child = {
            CircularImage(
                modifier = Modifier
                    .clickable { onEditAvatar() },
                painter = painterResource(profilePhoto.avatar),
                contentDescription = stringResource(R.string.profile_content_description),

                )
        }
    )
}

@Composable
fun AddEditUserName(
    focusRequester: FocusRequester,
    isAdd: Boolean,
    username: TextFieldValue ,
    onUserNameChanged: (TextFieldValue) -> Unit
) {

    DisneyOutlineTextField(
        modifier = Modifier.focusRequester(focusRequester),
        value = username,
        singleLine = true,
        onValueChange = {
            onUserNameChanged(it)
        },
        label = {
            Text(
                text = if (isAdd) stringResource(id = R.string.profile_name_label_add) else stringResource(
                    id = R.string.profile_name_label
                )
            )
        },
    )

}