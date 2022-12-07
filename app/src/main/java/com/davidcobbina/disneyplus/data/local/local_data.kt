package com.davidcobbina.disneyplus.data.local

import androidx.compose.ui.graphics.Color
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.model.MenuItem
import com.davidcobbina.disneyplus.model.Studio

fun getMenuItemsData(): List<MenuItem> {
    return arrayListOf(
        MenuItem("EveryThing", R.drawable.ic_layers, true),
        MenuItem("Movies", R.drawable.ic_film),
        MenuItem("Shows", R.drawable.ic_monitor),
        MenuItem("WatchList", R.drawable.ic_check),
        MenuItem("Downloads", R.drawable.ic_arrow_downward),
    )
}


fun getStudiosData(): List<Studio> {
    return arrayListOf(
        Studio("Star Wars", R.drawable.star_wars, color = Color.White),
        Studio("Marvel", R.drawable.marvel_logo),
        Studio("Disney", R.drawable.walt_disney),
        Studio("Pixar", R.drawable.pixar, color = Color.White),
        Studio("National Geographic", R.drawable.national_geograhic),
    )
}