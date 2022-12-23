package com.davidcobbina.disneyplus.navigation

import com.davidcobbina.disneyplus.data.local.model.Menu
import com.davidcobbina.disneyplus.data.local.model.UserProfile
import com.davidcobbina.disneyplus.data.remote.model.Movie

const val SPLASH_ROUTE = "splash"
const val LOGIN_CREATE_ACCOUNT_ROUTE = "login_create_account"
const val SELECT_ACCOUNT_ROUTE = "choose_account"
const val SELECT_AVATAR_ROUTE = "select_avatar"
const val ADD_EDIT_USER_ROUTE = "add_edit"
const val HOME_ROUTE = "home"
const val MENU_ROUTE = "menu"
const val DOWNLOADS_ROUTE = "downloads"
const val MOVIE_DETAIL_ROUTE = "movieDetail"
const val TV_SERIES_DETAIL_ROUTE = "tvSeriesDetail"
const val LIST_MOVIES_ROUTE = "listMovies"


const val MOVIE_DETAIL_ARGUMENT = "movie"
const val TV_SERIES_DETAIL_ARGUMENT = "tv"
const val LIST_MOVIES_ARGUMENT = "listMoviesTitle"
const val ADD_EDIT_ARGUMENT = "addEdit"
const val SELECT_AVATAR_ARGUMENT = "selected_avatar"
const val USER_PROFILE_ID_ARGUMENT = "userProfileId"


sealed class Screen(val route: String) {
    object SplashScreen : Screen(route = SPLASH_ROUTE)
    object LoginCreateAccountScreen : Screen(route = LOGIN_CREATE_ACCOUNT_ROUTE)
    object SelectAccountScreen : Screen(route = SELECT_ACCOUNT_ROUTE)
    object SelectAvatarScreen : Screen(route = "$SELECT_AVATAR_ROUTE/{$SELECT_AVATAR_ARGUMENT}") {
        fun passAvatar(avatar: Int): String {

            return "$SELECT_AVATAR_ROUTE/$avatar"
        }
    }

    object AddEditUserScreen :
        Screen(route = "$ADD_EDIT_USER_ROUTE/{$ADD_EDIT_ARGUMENT}/{$USER_PROFILE_ID_ARGUMENT}") {
        fun passAction(addOrEdit: Int, id: Int = -1): String {
            return "$ADD_EDIT_USER_ROUTE/$addOrEdit/$id"
        }
    }

    object HomeScreen : Screen(route = HOME_ROUTE)
    object MenuScreen : Screen(route = MENU_ROUTE)
    object DownloadsScreen : Screen(route = DOWNLOADS_ROUTE)
    object ListMoviesScreen :
        Screen(route = "$LIST_MOVIES_ROUTE/{$LIST_MOVIES_ARGUMENT}") {
        fun passMenu(menu: Menu): String {
            val data = menu.toString()
            return "$LIST_MOVIES_ROUTE/$data"
        }
    }

    object MovieDetailScreen : Screen(route = "$MOVIE_DETAIL_ROUTE/{$MOVIE_DETAIL_ARGUMENT}") {
        fun passMovie(movie: Movie): String {
            val data = movie.toString()
            return "$MOVIE_DETAIL_ROUTE/$data"
        }
    }

    object TvSeriesDetailScreen :
        Screen(route = "$TV_SERIES_DETAIL_ROUTE/{$TV_SERIES_DETAIL_ARGUMENT}") {
        fun passTvSeries(movie: Movie): String {
            val data = movie.toString()
            return "$TV_SERIES_DETAIL_ROUTE/$data"
        }
    }

}
