package com.davidcobbina.disneyplus.ui.screens.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.repositories.MoviesRepository
import com.davidcobbina.disneyplus.data.remote.model.Movie
import com.davidcobbina.disneyplus.data.local.model.AvatarCategory
import com.davidcobbina.disneyplus.data.local.model.AvatarProfile
import com.davidcobbina.disneyplus.data.repositories.LocalResourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TRENDING = "8230787"
const val STAR_WARS = "8230783"
const val MARVEL = "8230785"
const val DISNEY = "8230785"

data class HomeViewState(

    val avatarCategories: List<AvatarCategory> = arrayListOf(
        AvatarCategory(R.string.princess, isSelected = true),
        AvatarCategory(R.string.hero),
        AvatarCategory(R.string.villain),
        AvatarCategory(R.string.buddy),
    )
)

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val localResourcesRepository: LocalResourcesRepository,
    moviesRepository: MoviesRepository
) : ViewModel() {

    private val _moviesFeedLoading = MutableStateFlow(false)
    val moviesFeedLoading : StateFlow<Boolean>
    get() = _moviesFeedLoading

    private val _moviesFeed = MutableStateFlow(emptyList<Movie>())
    val moviesFeed : StateFlow<List<Movie>>
    get() = _moviesFeed

    init {
        viewModelScope.launch {
            _moviesFeedLoading.value = true
            val rMovies = moviesRepository.getMovieFeed()
            _moviesFeed.value = rMovies
            _moviesFeedLoading.value = false
        }
    }

    private val _avatars = MutableStateFlow(emptyList<AvatarProfile>())
    val avatars: StateFlow<List<AvatarProfile>> get() = _avatars

    init {
        viewModelScope.launch {
            _avatars.value = localResourcesRepository.getDisneyProfiles()
        }
    }
    var data by mutableStateOf(HomeViewState())

}