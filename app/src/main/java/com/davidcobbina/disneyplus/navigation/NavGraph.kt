package com.davidcobbina.disneyplus.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.davidcobbina.disneyplus.ui.screens.AddEditUserScreen
import com.davidcobbina.disneyplus.ui.screens.DownloadScreen
import com.davidcobbina.disneyplus.ui.screens.MenuScreen
import com.davidcobbina.disneyplus.ui.screens.SelectAccountScreen
import com.davidcobbina.disneyplus.ui.screens.home_screen.HomeScreen
import com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.MovieDetailScreen

@Composable
@ExperimentalMaterialApi
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SelectAccountScreen.route,
        builder = {
            composable(route = Screen.SelectAccountScreen.route) {
                SelectAccountScreen(navController)
            }
            composable(route = Screen.AddEditUserScreen.route) {
                AddEditUserScreen(navController)
            }
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(navController)
            }
            composable(route = Screen.MenuScreen.route) {
                MenuScreen(navController)
            }
            composable(route = Screen.DownloadsScreen.route) {
                DownloadScreen(navController)
            }
            composable(route = Screen.MovieDetailScreen.route) {
                MovieDetailScreen(navController)
            }
        },
    )
}