package com.davidcobbina.disneyplus.ui.screens.list_movies_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.data.MoviesRepository
import com.davidcobbina.disneyplus.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {

    private val _moviesFeedLoading = MutableStateFlow(false)
    val moviesFeedLoading: StateFlow<Boolean>
        get() = _moviesFeedLoading

    private val _moviesFeed = MutableStateFlow(emptyList<Movie>())
    val moviesFeed: StateFlow<List<Movie>>
        get() = _moviesFeed


    init {
        viewModelScope.launch {
            _moviesFeedLoading.value = true
            val rMovies = moviesRepository.getMovieFeed()
            _moviesFeed.value = rMovies
            _moviesFeedLoading.value = false

        }
    }
}