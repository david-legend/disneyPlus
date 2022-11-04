package com.davidcobbina.disneyplus.ui.screens.menu_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.model.FranchiseStudio
import com.davidcobbina.disneyplus.model.NavItem

data class HomeViewState(
    val navItems: List<NavItem> =  arrayListOf(
        NavItem(R.string.everything, R.drawable.ic_layers, true),
        NavItem(R.string.movies, R.drawable.ic_film),
        NavItem(R.string.shows, R.drawable.ic_monitor),
        NavItem(R.string.watchlist, R.drawable.ic_check),
        NavItem(R.string.downloads, R.drawable.ic_arrow_downward),
    ),
    val franchiseStudioList: List<FranchiseStudio>  = arrayListOf(
        FranchiseStudio(R.string.star_wars, R.drawable.star_wars, color = Color.White),
        FranchiseStudio(R.string.marvel, R.drawable.marvel_logo),
        FranchiseStudio(R.string.disney, R.drawable.walt_disney),
        FranchiseStudio(R.string.pixar, R.drawable.pixar, color = Color.White),
        FranchiseStudio(R.string.national_geographic, R.drawable.national_geograhic),
    )
)

class MenuViewModel() : ViewModel() {

    var data by mutableStateOf(HomeViewState())
}