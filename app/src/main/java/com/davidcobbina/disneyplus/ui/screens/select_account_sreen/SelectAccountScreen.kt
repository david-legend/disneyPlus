package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CircularImage
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.components.EditableItem
import com.davidcobbina.disneyplus.ui.screens.select_account_sreen.SelectAccountViewModel
import com.davidcobbina.disneyplus.ui.theme.blue
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import  androidx.lifecycle.viewmodel.compose.viewModel
import com.davidcobbina.disneyplus.model.DisneyUser


@Composable
fun SelectAccountScreen(
    navController: NavHostController,
    viewModel: SelectAccountViewModel = viewModel()
) {
    val screenHorizontalSpacing = dimensionResource(id = R.dimen.paddingMedium)
    val screenVerticalSpacing = dimensionResource(id = R.dimen.paddingLarge)
    val windowInfo = rememberWindowInfo()
    var editProfile by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier.padding(
            horizontal = screenHorizontalSpacing,
            vertical = screenVerticalSpacing
        )
    ) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Text(
                    text = stringResource(R.string.who_is_watching),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(screenVerticalSpacing * 4))
                UserProfileList(
                    windowInfo, editProfile,
                    onTapWithEdit = { navController.navigate(Screen.AddEditUserScreen.route) },
                    onTapWithoutEdit = { navController.navigate(Screen.HomeScreen.route) },
                    userAccounts = viewModel.data.userAccounts
                )

            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AddEditButtons(
                navController = navController,
                isEdit = editProfile,
                onEditChange = { editProfile = !it },
            )
        }

    }
}

@Composable
fun UserProfileList(
    windowInfo: WindowInfo,
    isEdit: Boolean,
    onTapWithoutEdit: () -> Unit,
    onTapWithEdit: () -> Unit,
    userAccounts: List<DisneyUser>
) {
    val horizontalScreenSpacing = dimensionResource(id = R.dimen.paddingMedium)
    val screenVerticalSpacing = dimensionResource(id = R.dimen.paddingLarge)
    val screenWidthWithoutPadding = windowInfo.screenWidthDp - (horizontalScreenSpacing * 2)

    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            crossAxisSpacing = screenVerticalSpacing
        ) {

            for (account in userAccounts) {
                EditableItem(
                    isEditable = isEdit,
                    child = {
                        CircularImage(
                            modifier = Modifier
                                .width(screenWidthWithoutPadding)
                                .clickable {
                                    if (isEdit) {
                                        onTapWithEdit()
                                    } else {
                                        onTapWithoutEdit()
                                    }
                                },
                            painter = painterResource(account.avatar),
                            contentDescription = stringResource(R.string.profile_content_description),
                            imageTitle = account.username
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
            for (account in userAccounts) {
                EditableItem(
                    isEditable = isEdit,
                    child = {
                        CircularImage(
                            modifier = Modifier
                                .width(itemWidth)
                                .clickable {
                                    if (isEdit) {
                                        onTapWithEdit()
                                    } else {
                                        onTapWithoutEdit()
                                    }
                                },
                            painter = painterResource(account.avatar),
                            contentDescription = stringResource(R.string.profile_content_description),
                            imageTitle = account.username
                        )
                    },
                )
            }
        }
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
                onClick = { navController.navigate(Screen.AddEditUserScreen.route) },
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

@Preview(showBackground = true)
@Composable
fun SelectAccountScreenPreview() {
    SelectAccountScreen(navController = rememberNavController())
}