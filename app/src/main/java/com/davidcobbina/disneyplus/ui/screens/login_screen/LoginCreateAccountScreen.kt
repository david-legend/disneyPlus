package com.davidcobbina.disneyplus.ui.screens.login_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.*


@Composable
fun LoginCreateAccountScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val paddingSpacing = dimensionResource(id = R.dimen.spacingMd)
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val textStyle = MaterialTheme.typography.titleMedium.copy(
        color = Color.White,
        fontSize = 18.sp, textAlign = TextAlign.Center,
    )
    val context = LocalContext.current
    val email by viewModel.email
    val saveEmailSuccessMessage = stringResource(id = R.string.save_email_success)
    val emailInvalidMessage = stringResource(id = R.string.email_invalid)

    LaunchedEffect(key1 = context) {
        viewModel.logUserInEvent.collect() { event ->
            when (event) {
                is LoginViewModel.LogUserInEvent.EmailChanged -> {
                    viewModel.updateEmail(event.email)
                }
                is LoginViewModel.LogUserInEvent.SaveUserEmail -> {
                    viewModel.saveEmail(event.email)
                }
                is LoginViewModel.LogUserInEvent.SavedEmailSuccess -> {
                    if (event.status) {
                        DisneyToast(context, saveEmailSuccessMessage)
                    } else {
                        DisneyToast(context, emailInvalidMessage)
                    }
                }
                is LoginViewModel.LogUserInEvent.NavigateToSelectAccountScreen -> {
                    navController.navigate(route = Screen.SelectAccountScreen.route)
                }
            }

        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
            .padding(
                vertical = paddingSpacing,
                horizontal = paddingSpacing
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            item {

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.topSpacingMd)))
                Text(
                    text = stringResource(id = R.string.login_heading_title),
                    style = MaterialTheme.typography.headlineMedium,
                )
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
                Text(
                    text = stringResource(id = R.string.login_heading_subtitle),
                    style = textStyle
                )

                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingXXl)))
                DisneyOutlineTextField(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .focusRequester(focusRequester),
                    value = email,
                    singleLine = true,
                    onValueChange = {
                        viewModel.onEmailChanged(it)
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.email_label)
                        )
                    },
                )
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingXXl)))
                DisneyButton(
                    onClick = { viewModel.onSaveEmail(email) },
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    content = {
                        Text(text = stringResource(id = R.string.get_started), style = textStyle)
                    }
                )
            }
        }

        CircularIconButton(
            child = {
                CustomIcon(
                    contentDescription = stringResource(R.string.add_button_content_description),
                    iconColor = Color.White,
                    icon = Icons.Filled.Close,
                )
            },
            buttonColor = MaterialTheme.colorScheme.surface,
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable {
                    //pop current screen off
                }
        )
    }
}