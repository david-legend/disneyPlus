package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.userAccounts
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CircularImage
import com.davidcobbina.disneyplus.ui.components.CustomIcon

//TODO:: Add bouncing animations to Profile Images
@Composable
fun SelectAccountScreen(navController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            horizontal = dimensionResource(id = R.dimen.paddingMedium),
            vertical = dimensionResource(id = R.dimen.paddingLarge)
        )
    ) {
        Text(
            text = stringResource(R.string.who_is_watching),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.weight(1.0f))
        for (account in userAccounts) {
            CircularImage(
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.HomeScreen.route)
                },
                painter = painterResource(account.avatar),
                contentDescription = stringResource(R.string.profile_content_description),
                imageTitle = account.username
            )
            Spacer(modifier = Modifier.weight(0.4f))
        }

        Spacer(modifier = Modifier.weight(1.0f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircularIconButton(
                child = {
                    CustomIcon(
                        contentDescription = stringResource(R.string.add_button_content_description),
                        iconColor = Color.White,
                        icon = Icons.Filled.Add
                    )
                },
                onClick = { /*TODO*/ },
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Text(text = stringResource(R.string.edit), style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectAccountScreenPreview() {
    SelectAccountScreen(navController = rememberNavController())
}