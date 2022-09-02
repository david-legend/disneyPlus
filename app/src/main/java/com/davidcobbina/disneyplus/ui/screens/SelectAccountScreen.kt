package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.davidcobbina.disneyplus.data.userAccounts
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CircularImage
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.theme.blue
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode




@Composable
fun SelectAccountScreen(navController: NavHostController) {
    val screenHorizontalSpacing = dimensionResource(id = R.dimen.paddingMedium)
    val screenVerticalSpacing = dimensionResource(id = R.dimen.paddingLarge)
    val windowInfo = rememberWindowInfo()
    val screenWidthWithoutPadding = windowInfo.screenWidth - (screenHorizontalSpacing * 2)

    Box(
        modifier = Modifier.padding(
            horizontal = screenHorizontalSpacing,
            vertical = screenVerticalSpacing
        )
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            item {
                Text(
                    text = stringResource(R.string.who_is_watching),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(screenVerticalSpacing * 4))
                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                    FlowRow(
                        mainAxisSize = SizeMode.Expand,
                        crossAxisSpacing = screenVerticalSpacing
                    ) {

                        for (account in userAccounts) {
                            CircularImage(
                                modifier = Modifier
                                    .width(screenWidthWithoutPadding)
                                    .clickable {
                                        navController.navigate(route = Screen.HomeScreen.route)
                                    },
                                painter = painterResource(account.avatar),
                                contentDescription = stringResource(R.string.profile_content_description),
                                imageTitle = account.username
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
                            CircularImage(
                                modifier = Modifier
                                    .width(itemWidth)
                                    .clickable {
                                        navController.navigate(route = Screen.HomeScreen.route)
                                    },
                                painter = painterResource(account.avatar),
                                contentDescription = stringResource(R.string.profile_content_description),
                                imageTitle = account.username
                            )
                        }
                    }
                }

            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AddEditButtons(navController = navController)
        }

    }
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.padding(
//            horizontal = dimensionResource(id = R.dimen.paddingMedium),
//            vertical = dimensionResource(id = R.dimen.paddingLarge)
//        )
//    ) {

//        for (account in userAccounts) {
//            CircularImage(
//                modifier = Modifier.clickable {
//                    navController.navigate(route = Screen.HomeScreen.route)
//                },
//                painter = painterResource(account.avatar),
//                contentDescription = stringResource(R.string.profile_content_description),
//                imageTitle = account.username
//            )
//            Spacer(modifier = Modifier.weight(0.4f))
//        }

//        Spacer(modifier = Modifier.weight(1.0f))

//    }
}


@Composable
fun AddEditButtons(navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
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
        Spacer(modifier = Modifier.weight(1.0f))
        Text(
            text = stringResource(R.string.edit),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp, color = blue
            ),
            modifier = Modifier.clickable {
                navController.navigate(Screen.AddEditUserScreen.route)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectAccountScreenPreview() {
    SelectAccountScreen(navController = rememberNavController())
}