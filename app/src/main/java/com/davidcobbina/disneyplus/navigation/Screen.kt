package com.davidcobbina.disneyplus.navigation

const val SELECT_ACCOUNT_ROUTE = "choose_account"
const val HOME_ROUTE = "home"
const val MENU_ROUTE = "menu"
const val DOWNLOADS_ROUTE = "downloads"
const val MOVIE_DETAIL_ROUTE = "movieDetail"

sealed class Screen(val route: String) {
    object SelectAccountScreen : Screen(route = SELECT_ACCOUNT_ROUTE)
    object HomeScreen : Screen(route = HOME_ROUTE)
    object MenuScreen : Screen(route = MENU_ROUTE)
    object DownloadsScreen : Screen(route = DOWNLOADS_ROUTE)
    object MovieDetailScreen : Screen(route = MOVIE_DETAIL_ROUTE)
}
