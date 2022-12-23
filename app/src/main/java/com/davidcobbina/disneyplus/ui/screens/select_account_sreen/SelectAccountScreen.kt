package com.davidcobbina.disneyplus.ui.screens.select_account_sreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.local.model.UserProfile
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.theme.blue
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.davidcobbina.disneyplus.data.local.stores.Profile
import com.davidcobbina.disneyplus.ui.components.*

const val EDIT = 0
const val ADD = 1

@Composable
fun SelectAccountScreen(
    navController: NavHostController,
    viewModel: SelectAccountViewModel = hiltViewModel()
) {
    val screenHorizontalSpacing = dimensionResource(id = R.dimen.paddingMedium)
    val screenVerticalSpacing = dimensionResource(id = R.dimen.paddingLarge)
    val windowInfo = rememberWindowInfo()
    var editProfile by rememberSaveable { mutableStateOf(false) }

    val profiles by viewModel.profiles.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadProfiles()
    }

    Box(
        modifier = Modifier.padding(
            horizontal = screenHorizontalSpacing,
            vertical = screenVerticalSpacing
        )
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    text = if (profiles.isEmpty()) stringResource(R.string.add_user_profile) else stringResource(
                        R.string.who_is_watching
                    ),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(screenVerticalSpacing))


                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (profiles.isEmpty()) {
                        Spacer(modifier = Modifier.height((windowInfo.screenHeight * 0.30).dp))
                        Text(
                            text = stringResource(id = R.string.no_profile_accounts_message),
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                            textAlign = TextAlign.Center,

                            )
                    } else {
                        UserProfileList(
                            windowInfo, editProfile,
                            onTapWithEdit = { profileId ->
                                navController.navigate(Screen.AddEditUserScreen.passAction(
                                EDIT, profileId)) },
                            onTapWithoutEdit = { navController.navigate(Screen.HomeScreen.route) },
                            userProfiles = profiles
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (profiles.isEmpty()) {
                AddUserProfile(navController = navController)
            } else {
                AddEditButtons(
                    navController = navController,
                    isEdit = editProfile,
                    onEditChange = { editProfile = !it },
                )
            }
        }

    }
}

@Composable
fun UserProfileList(
    windowInfo: WindowInfo,
    isEdit: Boolean,
    onTapWithoutEdit: () -> Unit,
    onTapWithEdit: (Int) -> Unit,
    userProfiles: List<UserProfile>
) {
    val horizontalScreenSpacing = dimensionResource(id = R.dimen.paddingMedium)
    val screenVerticalSpacing = dimensionResource(id = R.dimen.paddingLarge)
    val screenWidthWithoutPadding = windowInfo.screenWidthDp - (horizontalScreenSpacing * 2)


    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            crossAxisSpacing = screenVerticalSpacing
        ) {
            for (profile in userProfiles) {
                SelectableItem(
                    isSelected = isEdit,
                    child = {
                        CircularImage(
                            modifier = Modifier
                                .width(screenWidthWithoutPadding)
                                .clickable {
                                    if (isEdit) {
                                        onTapWithEdit(profile.id)
                                    } else {
                                        onTapWithoutEdit()
                                    }
                                },
                            painter = painterResource(profile.avatar),
                            contentDescription = stringResource(R.string.profile_content_description),
                            imageTitle = profile.username
                        )
                    }
                )

            }
        }
    } else {

        FlowRow(
            mainAxisSize = SizeMode.Expand,
            crossAxisSpacing = screenVerticalSpacing
        ) {
            val itemWidth = screenWidthWithoutPadding / 3
            for (profile in userProfiles) {
                SelectableItem(
                    isSelected = isEdit,
                    child = {
                        CircularImage(
                            modifier = Modifier
                                .width(itemWidth)
                                .clickable {
                                    if (isEdit) {
                                        onTapWithEdit(profile.id)
                                    } else {
                                        onTapWithoutEdit()
                                    }
                                },
                            painter = painterResource(profile.avatar),
                            contentDescription = stringResource(R.string.profile_content_description),
                            imageTitle = profile.username
                        )
                    },
                )
            }
        }
    }

}


@Composable
fun NoProfileAccounts() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.no_profile_accounts_message),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AddEditButtons(
    navController: NavHostController,
    isEdit: Boolean,
    onEditChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        if (!isEdit) {
            CircularIconButton(
                child = {
                    CustomIcon(
                        contentDescription = stringResource(R.string.add_button_content_description),
                        iconColor = Color.White,
                        icon = Icons.Filled.Add
                    )
                },
                onClick = { navController.navigate(Screen.AddEditUserScreen.passAction(ADD)) },
            )
        }

        Spacer(modifier = Modifier.weight(1.0f))
        Text(
            text = if (isEdit) stringResource(R.string.done) else stringResource(R.string.edit),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp, color = blue
            ),
            modifier = Modifier.clickable {
                onEditChange(isEdit)
            }
        )
    }
}

@Composable
fun AddUserProfile(
    navController: NavHostController,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        CircularIconButton(
            child = {
                CustomIcon(
                    contentDescription = stringResource(R.string.add_button_content_description),
                    iconColor = Color.White,
                    icon = Icons.Filled.Add
                )
            },
            onClick = { navController.navigate(Screen.AddEditUserScreen.passAction(ADD)) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectAccountScreenPreview() {
    SelectAccountScreen(navController = rememberNavController())
}