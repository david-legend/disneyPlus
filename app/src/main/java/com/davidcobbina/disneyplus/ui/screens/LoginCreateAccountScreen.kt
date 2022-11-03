package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.components.DisneyButton
import com.davidcobbina.disneyplus.ui.components.DisneyOutlineTextField

@Composable
fun LoginCreateAccountScreen(navController: NavHostController) {
    val paddingSpacing = dimensionResource(id = R.dimen.spacingMd)
    val userNameState = remember { mutableStateOf(TextFieldValue()) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val textStyle = MaterialTheme.typography.titleMedium.copy(
        color = Color.White,
        fontSize = 18.sp, textAlign = TextAlign.Center,
    )

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
                    value = userNameState.value,
                    singleLine = true,
                    onValueChange = { userNameState.value = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.email_label)
                        )
                    },
                )
                Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingXXl)))
                DisneyButton(
                    onClick = { navController.navigate(route = Screen.SelectAccountScreen.route) },
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