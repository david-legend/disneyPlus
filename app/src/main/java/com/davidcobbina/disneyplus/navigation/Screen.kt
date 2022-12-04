package com.davidcobbina.disneyplus.navigation

import com.davidcobbina.disneyplus.data.model.Movie

const val SPLASH_ROUTE = "splash"
const val LOGIN_CREATE_ACCOUNT_ROUTE = "login_create_account"
const val SELECT_ACCOUNT_ROUTE = "choose_account"
const val ADD_EDIT_USER_ROUTE = "add_edit"
const val HOME_ROUTE = "home"
const val MENU_ROUTE = "menu"
const val DOWNLOADS_ROUTE = "downloads"
const val MOVIE_DETAIL_ROUTE = "movieDetail"
const val TV_SERIES_DETAIL_ROUTE = "tvSeriesDetail"


const val MOVIE_DETAIL_ARGUMENT = "movie"
const val TV_SERIES_DETAIL_ARGUMENT = "tv"



sealed class Screen(val route: String) {
    object SplashScreen : Screen(route = SPLASH_ROUTE)
    object LoginCreateAccountScreen : Screen(route = LOGIN_CREATE_ACCOUNT_ROUTE)
    object SelectAccountScreen : Screen(route = SELECT_ACCOUNT_ROUTE)
    object AddEditUserScreen : Screen(route = ADD_EDIT_USER_ROUTE)
    object HomeScreen : Screen(route = HOME_ROUTE)
    object MenuScreen : Screen(route = MENU_ROUTE)
    object DownloadsScreen : Screen(route = DOWNLOADS_ROUTE)
    object MovieDetailScreen : Screen(route = "$MOVIE_DETAIL_ROUTE/{$MOVIE_DETAIL_ARGUMENT}") {
        fun parseMovie(movie: Movie) : String {
            val data = movie.toString()
            return "$MOVIE_DETAIL_ROUTE/$data"
        }
    }
    object TvSeriesDetailScreen : Screen(route = "$TV_SERIES_DETAIL_ROUTE/{$TV_SERIES_DETAIL_ARGUMENT}") {
        fun parseMovie(movie: Movie) : String {
            val data = movie.toString()
            return "$TV_SERIES_DETAIL_ROUTE/$data"
        }
    }

}
