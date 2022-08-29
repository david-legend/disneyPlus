package com.davidcobbina.disneyplus.data

import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.graphics.Color
import com.davidcobbina.disneyplus.R

data class DisneyMovie(val movieCover: Int)
data class AvatarProfile(val avatar: Int)
data class FranchiseStudio(val title: Int, val logo: Int, val color: Color? = null)
data class NavItem(val title: Int, val icon: Int, val isSelected: Boolean = false)

data class AvatarCategory(val title: Int, val isSelected: Boolean = false)
data class DownloadedMovie(
    val movieCover: Int,
    val title: String,
    val yearReleased: String,
    val downloadedSize: String,
    val isSeries: Boolean = false,
    val numberOfEpisodes: Int? = 0
)

val AvatarCategoriesSaver = listSaver<AvatarCategory, Any>(
    save = { listOf(it.title, it.isSelected) },
    restore = { AvatarCategory(it[0] as Int, it[1] as Boolean) }
)

var suggestedMovieList = List(15) { DisneyMovie(R.drawable.mandalorian) }
var avatarProfilesList = arrayListOf(
    AvatarProfile(R.drawable.pocahontas),
    AvatarProfile(R.drawable.scar),
    AvatarProfile(R.drawable.timon),
    AvatarProfile(R.drawable.simba_kid),
)


// Navigation List
var navItemsList = arrayListOf(
    NavItem(R.string.everything, R.drawable.ic_layers, true),
    NavItem(R.string.movies, R.drawable.ic_film),
    NavItem(R.string.shows, R.drawable.ic_monitor),
    NavItem(R.string.watchlist, R.drawable.ic_check),
    NavItem(R.string.downloads, R.drawable.ic_arrow_downward),
)

var franchiseStudioList = arrayListOf(
    FranchiseStudio(R.string.star_wars, R.drawable.star_wars, color = Color.White),
    FranchiseStudio(R.string.marvel, R.drawable.marvel_logo),
    FranchiseStudio(R.string.disney, R.drawable.walt_disney),
    FranchiseStudio(R.string.pixar, R.drawable.pixar, color = Color.White),
    FranchiseStudio(R.string.national_geographic, R.drawable.national_geograhic),
)


var avatarCategories = arrayListOf(
    AvatarCategory(R.string.princess, isSelected = true),
    AvatarCategory(R.string.hero),
    AvatarCategory(R.string.villain),
    AvatarCategory(R.string.buddy),
)

var downloadedMovies = List(15) {
    DownloadedMovie(
        movieCover = R.drawable.mandalorian,
        title = "The Mandalorian",
        yearReleased = "2019",
        downloadedSize = "2.7Gb"
    )
}

var movieDetailCover = arrayListOf(
    DisneyMovie(R.drawable.mandalorian_sunny),
//    DisneyMovie(R.drawable.mandalorian_drive),
)
