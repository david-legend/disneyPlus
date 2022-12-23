package com.davidcobbina.disneyplus.ui.screens.menu_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.local.DOWNLOADS
import com.davidcobbina.disneyplus.data.local.EVERYTHING
import com.davidcobbina.disneyplus.data.local.WATCHLIST
import com.davidcobbina.disneyplus.data.local.model.Menu
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.components.NavItem


val topSpacing: Dp = 250.dp
val mediumSpacing: Dp = 60.dp
val footerSpacing: Dp = 120.dp

@Composable
fun MenuScreen(navController: NavHostController, viewModel: MenuViewModel = hiltViewModel()) {
    val windowInfo = rememberWindowInfo()

    val navItems by viewModel.menuItems.collectAsState()
    val studios by viewModel.studios.collectAsState()


    val lifecycleOwner = LocalLifecycleOwner.current
    lifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.menuEvent.collect { event ->
            when (event) {
                is MenuViewModel.MenuEvent.NavigateToScreenSelected -> {
                    viewModel.updateMenuList(event.menu.title)
                    when (event.menu.title) {
                        DOWNLOADS -> {
                            navController.navigate(route = Screen.DownloadsScreen.route)
                        }
                        EVERYTHING -> {
                            navController.navigate(route = Screen.HomeScreen.route)
                        }
                        WATCHLIST -> {}
                        else -> {
                            navController.navigate(
                                route = Screen.ListMoviesScreen.passMenu(event.menu)
                            )
                        }
                    }
                }
            }
        }
    }

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
            if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                Box(modifier = Modifier.height(topSpacing))
            } else {
                Box(modifier = Modifier.height(mediumSpacing))
            }
            NavigationList(navItems) { menu ->
                viewModel.onMenuItemSelected(menu)
            }
            Box(modifier = Modifier.height(mediumSpacing))
            StudioList(studios) { menu ->
                viewModel.onMenuItemSelected(menu)
            }
            Box(modifier = Modifier.height(mediumSpacing))
            Text(
                text = stringResource(id = R.string.categories),
                style = MaterialTheme.typography.titleMedium,
            )
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
            Text(
                text = stringResource(id = R.string.coming_soon),
                style = MaterialTheme.typography.titleMedium,
            )
            Box(modifier = Modifier.height(footerSpacing))
        }
        CloseMenuButton(navController)

    }
}


@Composable
fun NavigationList(menuItems: List<Menu>, onMenuItemClicked: (Menu) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(fraction = 1f)
    ) {
        menuItems.forEach { menu ->
            NavItem(
                painter = painterResource(id = menu.icon),
                title = menu.title,
                textStyle = MaterialTheme.typography.titleMedium,
                contentDescription = menu.title,
                isSelected = menu.isSelected,
                modifier = Modifier.clickable {
                    onMenuItemClicked(menu)
                }
            )
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
        }
    }
}

@Composable
fun StudioList(franchiseStudioData: List<Menu>, onStudioItemClicked: (Menu) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(fraction = 1f)
    ) {
        franchiseStudioData.forEach { item ->
            val colorFilter =
                if (item.color != null) ColorFilter.tint(color = item.color) else null
            Image(
                painter = painterResource(id = item.icon),
                colorFilter = colorFilter,
                contentDescription = item.title,
                modifier = Modifier.clickable {
                    onStudioItemClicked(item)
                }
            )
            Box(modifier = Modifier.height(mediumSpacing))
        }
    }
}

@Composable
fun CloseMenuButton(navController: NavHostController) {
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
            onClick = { navController.popBackStack() }
        )
    }
}