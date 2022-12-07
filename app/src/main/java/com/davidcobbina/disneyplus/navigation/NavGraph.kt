package com.davidcobbina.disneyplus.navigation

import ListMoviesScreen
import MenuScreen
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.davidcobbina.disneyplus.data.model.Movie
import com.davidcobbina.disneyplus.ui.screens.*
import com.davidcobbina.disneyplus.ui.screens.home_screen.HomeScreen
import com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.MovieDetailScreen
import com.davidcobbina.disneyplus.ui.screens.tv_series_detail_screen.TvSeriesDetailScreen
import com.google.gson.Gson

@Composable
@ExperimentalMaterialApi
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MenuScreen.route,
        builder = {
            composable(route = Screen.SplashScreen.route) {
                SplashScreen(navController = navController)
            }
            composable(route = Screen.LoginCreateAccountScreen.route) {
                LoginCreateAccountScreen(navController)
            }
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
            composable(route = Screen.ListMoviesScreen.route) {
                ListMoviesScreen(navController)
            }
            composable(
                route = Screen.MovieDetailScreen.route,
                arguments = listOf(navArgument("movie") {
                    type = MovieArgType()
                })
            ) { navBackStackEntry->
                val movie = navBackStackEntry.arguments?.getString("movie").let { Gson().fromJson(it, Movie::class.java) }
                MovieDetailScreen(navController, movie)
            }
            composable(
                route = Screen.TvSeriesDetailScreen.route,
                arguments = listOf(navArgument("tv") {
                    type = MovieArgType()
                })
            ) { navBackStackEntry->
                val movie = navBackStackEntry.arguments?.getString("tv").let { Gson().fromJson(it, Movie::class.java) }
                TvSeriesDetailScreen(navController, movie)
            }
        },
    )
}