package com.davidcobbina.disneyplus.navigation

import ListMoviesScreen
import com.davidcobbina.disneyplus.ui.screens.menu_screen.MenuScreen
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.davidcobbina.disneyplus.data.local.model.Menu
import com.davidcobbina.disneyplus.data.remote.model.Movie
import com.davidcobbina.disneyplus.ui.screens.*
import com.davidcobbina.disneyplus.ui.screens.add_edit_user_screen.AddEditUserScreen
import com.davidcobbina.disneyplus.ui.screens.select_avatar_screen.SelectAvatarScreen
import com.davidcobbina.disneyplus.ui.screens.home_screen.HomeScreen
import com.davidcobbina.disneyplus.ui.screens.login_screen.LoginCreateAccountScreen
import com.davidcobbina.disneyplus.ui.screens.movie_detail_screen.MovieDetailScreen
import com.davidcobbina.disneyplus.ui.screens.select_account_sreen.SelectAccountScreen
import com.davidcobbina.disneyplus.ui.screens.splash_screen.SplashScreen
import com.davidcobbina.disneyplus.ui.screens.tv_series_detail_screen.TvSeriesDetailScreen
import com.google.gson.Gson

@Composable
@ExperimentalMaterialApi
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
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
            composable(route = Screen.SelectAvatarScreen.route,
                arguments = listOf(
                    navArgument(SELECT_AVATAR_ARGUMENT) {
                        type = NavType.IntType
                    }
                )
            ) {
                SelectAvatarScreen(navController)
            }
            composable(
                route = Screen.AddEditUserScreen.route,
                arguments = listOf(
                    navArgument(ADD_EDIT_ARGUMENT) {
                        type = NavType.IntType
                    },
                    navArgument(USER_PROFILE_ID_ARGUMENT) {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) {
                AddEditUserScreen(navController)
            }
            composable(route = Screen.HomeScreen.route, arguments = listOf(
                navArgument(USER_PROFILE_ID_ARGUMENT) {
                    type = NavType.IntType
                }
            )) {
                HomeScreen(navController)
            }
            composable(route = Screen.MenuScreen.route) {
                MenuScreen(navController)
            }
            composable(route = Screen.DownloadsScreen.route) {
                DownloadScreen(navController)
            }
            composable(
                route = Screen.ListMoviesScreen.route,
                arguments = listOf(
                    navArgument(LIST_MOVIES_ARGUMENT) {
                        type = MenuArgType()
                    }
                )
            ) { navBackStackEntry ->
                val menu = navBackStackEntry.arguments?.getString(LIST_MOVIES_ARGUMENT)
                    .let { Gson().fromJson(it, Menu::class.java) }
                ListMoviesScreen(navController, menu)
            }
            composable(
                route = Screen.MovieDetailScreen.route,
                arguments = listOf(navArgument(MOVIE_DETAIL_ARGUMENT) {
                    type = MovieArgType()
                })
            ) { navBackStackEntry ->
                val movie = navBackStackEntry.arguments?.getString(MOVIE_DETAIL_ARGUMENT)
                    .let { Gson().fromJson(it, Movie::class.java) }

                MovieDetailScreen(navController, movie)
            }
            composable(
                route = Screen.TvSeriesDetailScreen.route,
                arguments = listOf(navArgument(TV_SERIES_DETAIL_ARGUMENT) {
                    type = MovieArgType()
                })
            ) { navBackStackEntry ->
                val movie = navBackStackEntry.arguments?.getString(TV_SERIES_DETAIL_ARGUMENT)
                    .let { Gson().fromJson(it, Movie::class.java) }
                TvSeriesDetailScreen(navController, movie)
            }
        },
    )
}