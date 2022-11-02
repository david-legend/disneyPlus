package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.settingsList
import com.davidcobbina.disneyplus.ui.components.*

@Composable
fun AddEditUserScreen(navController: NavHostController) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val autoPlayEpisodeState = remember { mutableStateOf(true) }
    val autoPlayPreviewsState = remember { mutableStateOf(true) }

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
                AddEditUserAppBar(navController)
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                EditProfilePhoto()
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                AddEditUserName(focusRequester)
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingXXl)))

            }

            itemsIndexed(settingsList) { _, settings ->
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
fun AddEditUserAppBar(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(onClick = { navController.popBackStack()}) {
            Text(
                text = stringResource(id = R.string.cancel),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        }

        Text(
            text = stringResource(id = R.string.edit_profile),
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        )
        TextButton(onClick = { navController.popBackStack()}) {
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
fun EditProfilePhoto() {
    EditableItem(
        isEditable = true,
        child = {
            CircularImage(
                modifier = Modifier
                    .clickable {

                    },
                painter = painterResource(R.drawable.moana),
                contentDescription = stringResource(R.string.profile_content_description),

                )
        }
    )
}

@Composable
fun AddEditUserName(focusRequester: FocusRequester) {
    val userNameState = remember { mutableStateOf(TextFieldValue()) }
    DisneyOutlineTextField(
        modifier = Modifier.focusRequester(focusRequester),
        value = userNameState.value,
        singleLine = true,
        onValueChange = { userNameState.value = it },
        label = {
            Text(
                text = stringResource(id = R.string.profile_name_label)
            )
        },
    )

}