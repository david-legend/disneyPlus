package com.davidcobbina.disneyplus.data

import androidx.compose.ui.graphics.Color
import com.davidcobbina.disneyplus.R

data class DisneyMovie(val movieCover: Int)
data class AvatarProfile(val avatar: Int)
data class FranchiseStudio(val title: Int, val logo: Int, val color: Color? = null)
data class NavItem(val title: Int, val icon: Int, val isSelected: Boolean = false)

var suggestedMovieList = List(15) { DisneyMovie(R.drawable.mandalorian) }
var avatarList = arrayListOf(
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

