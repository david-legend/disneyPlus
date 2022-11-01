package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.settingsList
import com.davidcobbina.disneyplus.ui.components.*

@Composable
fun AddEditUserScreen(navController: NavHostController) {
    val autoPlayEpisodeState = remember { mutableStateOf(true) }
    val autoPlayPreviewsState = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AddEditUserAppBar()
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                EditProfilePhoto()
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
//                AddEditUserName()

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
fun AddEditUserAppBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Text(text = "Cancel")
        Text(text = "Edit Profile")
        Text(text = "Done")
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

//@Composable
//fun AddEditUserName() {
//    TextField(
//        modifier = modifier,
//        value = email ?: "",
//        onValueChange = { email ->
//            onEmailChanged(email)
//        },
//        label = {
//            Text(text = stringResource(
//                id = R.string.label_email)
//            )
//        },
//        singleLine = true
//    )
//}