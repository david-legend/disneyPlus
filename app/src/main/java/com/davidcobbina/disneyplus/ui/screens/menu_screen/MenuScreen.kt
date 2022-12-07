

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
import com.davidcobbina.disneyplus.model.Studio
import com.davidcobbina.disneyplus.model.MenuItem
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.CircularIconButton
import com.davidcobbina.disneyplus.ui.components.CustomIcon
import com.davidcobbina.disneyplus.ui.components.NavItem
import com.davidcobbina.disneyplus.ui.screens.menu_screen.MenuViewModel


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
                    viewModel.updateMenuList(event.id)
                    navController.navigate(route = Screen.ListMoviesScreen.route)
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
            NavigationList(navItems) {
                viewModel.onMenuItemSelected(it)
            }
            Box(modifier = Modifier.height(mediumSpacing))
            FranchiseStudioList(studios)
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
fun NavigationList(menuItems: List<MenuItem>, onNavItemClicked: (String) -> Unit) {
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
                    onNavItemClicked(menu.title)
                }
            )
            Box(modifier = Modifier.height(dimensionResource(id = R.dimen.spacingMd)))
        }
    }
}

@Composable
fun FranchiseStudioList(franchiseStudioData: List<Studio>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(fraction = 1f)
    ) {
        franchiseStudioData.forEach { item ->
            val colorFilter =
                if (item.color != null) ColorFilter.tint(color = item.color) else null
            Image(
                painter = painterResource(id = item.logo),
                colorFilter = colorFilter,
                contentDescription = item.title,
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