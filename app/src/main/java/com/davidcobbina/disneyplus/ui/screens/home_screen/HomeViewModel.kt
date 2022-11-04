package com.davidcobbina.disneyplus.ui.screens.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.model.AvatarCategory
import com.davidcobbina.disneyplus.model.AvatarProfile
import com.davidcobbina.disneyplus.model.DisneyMovie

data class HomeViewState(
    val suggestedMovies: List<DisneyMovie> = List(15) { DisneyMovie(R.drawable.mandalorian) },
    val avatarProfiles: List<AvatarProfile> = arrayListOf(
        AvatarProfile(R.drawable.merida),
        AvatarProfile(R.drawable.moana),
        AvatarProfile(R.drawable.olaf),
        AvatarProfile(R.drawable.mr_incredible),
        AvatarProfile(R.drawable.mushu),
        AvatarProfile(R.drawable.simba),
    ),
    val avatarCategories : List<AvatarCategory> = arrayListOf(
        AvatarCategory(R.string.princess, isSelected = true),
        AvatarCategory(R.string.hero),
        AvatarCategory(R.string.villain),
        AvatarCategory(R.string.buddy),
    )
)

class HomeViewModel() : ViewModel() {

    var data by mutableStateOf(HomeViewState())

}