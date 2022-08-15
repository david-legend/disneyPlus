package com.davidcobbina.disneyplus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.FranchiseStudioData
import com.davidcobbina.disneyplus.data.NavItemData
import com.davidcobbina.disneyplus.data.franchiseStudioList
import com.davidcobbina.disneyplus.data.navItemsList
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.NavItem

@Composable
fun NavigationDrawer() {
    Box() {
        Column(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.paddingExtraLarge))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.height(180.dp))
            NavigationList(navItemsList)
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginExtraLarge)))
            FranchiseStudioList(franchiseStudioList)
            Box(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(id = R.string.categories),
                style = MaterialTheme.typography.titleLarge,
            )
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
            Text(
                text = stringResource(id = R.string.coming_soon),
                style = MaterialTheme.typography.titleLarge,
            )
            Box(modifier = Modifier.height(50.dp))
        }
        CloseNavigationButton()

    }
}


@Composable
fun NavigationList(navItems: List<NavItemData>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(fraction = 1f)
    ) {
        navItems.forEach { navItem ->
            NavItem(
                painter = painterResource(id = navItem.icon),
                title = stringResource(id = navItem.title),
                textStyle = MaterialTheme.typography.titleLarge,
                contentDescription = stringResource(id = navItem.title)
            )
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.marginLarge)))
        }
    }
}

@Composable
fun FranchiseStudioList(franchiseStudioData: List<FranchiseStudioData>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(fraction = 1f)
    ) {
        franchiseStudioData.forEach { item ->
            val colorFilter =
                if (item.color != null) ColorFilter.tint(color = item.color) else null;
            Image(
                painter = painterResource(id = item.logo),
                colorFilter = colorFilter,
                contentDescription = stringResource(id = item.title),
            )
            Box(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun CloseNavigationButton() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(fraction = 1f)
            .padding(
                vertical = dimensionResource(
                    id = R.dimen.marginLarge
                )
            )
    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        CircularIconButton(
            icon = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_drawer_icon),
            onClick = {/*TODO*/ }
        )
    }
}