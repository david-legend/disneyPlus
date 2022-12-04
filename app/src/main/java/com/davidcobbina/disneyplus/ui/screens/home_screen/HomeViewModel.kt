package com.davidcobbina.disneyplus.ui.screens.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.MoviesRepository
import com.davidcobbina.disneyplus.data.model.Movie
import com.davidcobbina.disneyplus.model.AvatarCategory
import com.davidcobbina.disneyplus.model.AvatarProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeViewState(
    val avatarProfiles: List<AvatarProfile> = arrayListOf(
        AvatarProfile(R.drawable.merida),
        AvatarProfile(R.drawable.moana),
        AvatarProfile(R.drawable.olaf),
        AvatarProfile(R.drawable.mr_incredible),
        AvatarProfile(R.drawable.mushu),
        AvatarProfile(R.drawable.simba),
    ),
    val avatarCategories: List<AvatarCategory> = arrayListOf(
        AvatarCategory(R.string.princess, isSelected = true),
        AvatarCategory(R.string.hero),
        AvatarCategory(R.string.villain),
        AvatarCategory(R.string.buddy),
    )
)

@HiltViewModel
class HomeViewModel @Inject constructor(
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

    var data by mutableStateOf(HomeViewState())

}