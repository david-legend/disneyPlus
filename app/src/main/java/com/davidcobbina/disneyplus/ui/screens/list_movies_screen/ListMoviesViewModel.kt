package com.davidcobbina.disneyplus.ui.screens.list_movies_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.data.local.model.Menu
import com.davidcobbina.disneyplus.data.repositories.MoviesRepository
import com.davidcobbina.disneyplus.data.remote.model.Movie
import com.davidcobbina.disneyplus.navigation.LIST_MOVIES_ARGUMENT
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val menu = checkNotNull(savedStateHandle.get<String>(LIST_MOVIES_ARGUMENT)).let {
        Gson().fromJson(
            it,
            Menu::class.java
        )
    }


    private val _moviesFeedLoading = MutableStateFlow(false)
    val moviesFeedLoading: StateFlow<Boolean>
        get() = _moviesFeedLoading

    private val _moviesFeed = MutableStateFlow(emptyList<Movie>())
    val moviesFeed: StateFlow<List<Movie>>
        get() = _moviesFeed


    init {
        viewModelScope.launch {
            _moviesFeedLoading.value = true
            val rMovies = moviesRepository.getMovieFeed(menu.apiId)
            _moviesFeed.value = rMovies
            _moviesFeedLoading.value = false

        }
    }
}