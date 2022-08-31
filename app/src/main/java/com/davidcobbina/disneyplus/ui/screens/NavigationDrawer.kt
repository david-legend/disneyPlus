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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.FranchiseStudio
import com.davidcobbina.disneyplus.data.NavItem
import com.davidcobbina.disneyplus.data.franchiseStudioList
import com.davidcobbina.disneyplus.data.navItemsList
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.components.NavItem


val topSpacing: Dp = 250.dp
val mediumSpacing: Dp = 60.dp
val footerSpacing: Dp = 120.dp

@Composable
fun NavigationDrawer() {
    Box(
//        modifier = Modifier
//            .background(
//                brush = Brush.verticalGradient(
//                    colorStops = arrayOf(
//                        Pair(0.20f, Color.Transparent),
//                        Pair(0.70f, Color(0x961C1C1E)),
//                        Pair(0.98f, black),
//                    )
//
//                )
//            )
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.height(topSpacing))
            NavigationList(navItemsList)
            Box(modifier = Modifier.height(mediumSpacing))
            FranchiseStudioList(franchiseStudioList)
            Box(modifier = Modifier.height(mediumSpacing))
            Text(
                text = stringResource(id = R.string.categories),
                style = MaterialTheme.typography.titleLarge,
            )
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
            Text(
                text = stringResource(id = R.string.coming_soon),
                style = MaterialTheme.typography.titleLarge,
            )
            Box(modifier = Modifier.height(footerSpacing))
        }
        CloseNavigationButton()

    }
}


@Composable
fun NavigationList(navItems: List<NavItem>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(fraction = 1f)
    ) {
        navItems.forEach { navItem ->
            NavItem(
                painter = painterResource(id = navItem.icon),
                title = stringResource(id = navItem.title),
                textStyle = MaterialTheme.typography.titleLarge,
                contentDescription = stringResource(id = navItem.title),
                isSelected = navItem.isSelected,
            )
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
        }
    }
}

@Composable
fun FranchiseStudioList(franchiseStudioData: List<FranchiseStudio>) {
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
            Box(modifier = Modifier.height(mediumSpacing))
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
                    id = R.dimen.spacingMd
                )
            )
    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        CircularIconButton(
            child = {
                CustomIcon(
                    icon = Icons.Filled.Close,
                    contentDescription = stringResource(id = R.string.close_drawer_icon),
                )
            },
            onClick = {/*TODO*/ }
        )
    }
}