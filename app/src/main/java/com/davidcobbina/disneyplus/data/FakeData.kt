package com.davidcobbina.disneyplus.data

import androidx.compose.ui.graphics.Color
import com.davidcobbina.disneyplus.R

data class MovieData(val movieCover: Int)
data class FranchiseStudioData(val title: Int, val logo: Int, val color: Color? = null)
data class NavItemData(val title: Int, val icon: Int, val isSelected: Boolean = false)

var suggestedMovieList = List(15) { MovieData(R.drawable.mandalorian) }


// Navigation List
var navItemsList = arrayListOf(
    NavItemData(R.string.everything, R.drawable.ic_layers, true),
    NavItemData(R.string.movies, R.drawable.ic_film),
    NavItemData(R.string.shows, R.drawable.ic_monitor),
    NavItemData(R.string.watchlist, R.drawable.ic_check),
    NavItemData(R.string.downloads, R.drawable.ic_arrow_downward),
)

var franchiseStudioList = arrayListOf(
    FranchiseStudioData(R.string.star_wars, R.drawable.star_wars, color = Color.White),
    FranchiseStudioData(R.string.marvel, R.drawable.marvel_logo),
    FranchiseStudioData(R.string.disney, R.drawable.walt_disney),
    FranchiseStudioData(R.string.pixar, R.drawable.pixar, color = Color.White),
    FranchiseStudioData(R.string.national_geographic, R.drawable.national_geograhic),
)

