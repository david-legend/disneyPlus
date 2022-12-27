package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.repositories.MoviesRepository
import com.davidcobbina.disneyplus.data.remote.api.ApiConstants
import com.davidcobbina.disneyplus.data.remote.model.*
import com.davidcobbina.disneyplus.data.local.model.ActionList
import com.davidcobbina.disneyplus.data.local.model.DisneyMovie
import com.davidcobbina.disneyplus.util.covertMinutesToHourMinute
import com.davidcobbina.disneyplus.util.extractDataFromArray
import com.davidcobbina.disneyplus.util.parseYearFromDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieDetailState(

    val trailers: List<DisneyMovie> = List(5) {
        DisneyMovie(R.drawable.mandalorian_cover)
    },
    val actionList: List<ActionList> = arrayListOf(
        ActionList("Download", R.drawable.ic_arrow_down),
        ActionList("Share", R.drawable.ic_share),
        ActionList("More Like This", R.drawable.ic_search),
        ActionList("Cast on TV", R.drawable.ic_cast),
    ),

)

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val movieDetailEventChannel = Channel<MovieDetailEvent>()
    val movieDetailEvent = movieDetailEventChannel.receiveAsFlow()

    private val _movieDetailLoading = MutableStateFlow(false)
    val movieDetailLoading: StateFlow<Boolean>
        get() = _movieDetailLoading

    private val _movieDetail = MutableStateFlow<MovieDetail?>(null)
    val movieDetail: StateFlow<MovieDetail?>
        get() = _movieDetail

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            _movieDetailLoading.value = true
            val detail = moviesRepository.getMovieDetail(movieId)
            detail.metaData = processMetaData(detail)
            _movieDetail.value = detail
            _movieDetailLoading.value = false

        }
    }


    private val _similarMoviesLoading = MutableStateFlow(false)
    val similarMoviesLoading: StateFlow<Boolean>
        get() = _similarMoviesLoading

    private val _similarMovies = MutableStateFlow(emptyList<Movie>())
    val similarMovies: StateFlow<List<Movie>>
        get() = _similarMovies

    fun getRecommendations(movieId: String) {
        viewModelScope.launch {
            _similarMoviesLoading.value = true
            val similarMovies =  moviesRepository.getSimilarMovies(movieId)
            _similarMovies.value = similarMovies
            _similarMoviesLoading.value = false

        }
    }

    private val _movieCredits = MutableStateFlow<MovieCredits?>(null)
    val movieCredits: StateFlow<MovieCredits?>
        get() = _movieCredits

    fun getMovieCredits(movieId: String) {
        viewModelScope.launch {
            val credits = moviesRepository.getMovieCredits(movieId)
            credits.primaryCast = processCast(credits.cast)
            _movieCredits.value = credits
        }
    }

    private val _trailersLoading = MutableStateFlow(false)
    val trailersLoading: StateFlow<Boolean>
        get() = _trailersLoading

    private val _movieTrailers = MutableStateFlow(emptyList<Trailer>())
    val movieTrailers: StateFlow<List<Trailer>>
        get() = _movieTrailers

    fun getMovieTrailers(movieId: String) {
        viewModelScope.launch {
            _trailersLoading.value = true
            val movieTrailers =  moviesRepository.getMovieTrailers(movieId)
            _movieTrailers.value = movieTrailers
            _trailersLoading.value = false

        }
    }


    private fun processMetaData(detail: MovieDetail): List<String> {
        val metaData = mutableListOf<String>()
        metaData.add(parseYearFromDate(detail.releaseDate))
        metaData.add(covertMinutesToHourMinute(detail.runtime))
        metaData.add("CC")
        metaData.add("HD")
        return metaData
    }

    private fun processCast(cast: List<Cast>): List<Department> {
        val result = mutableListOf<Department>()
        result.add(
            Department(
                type = ApiConstants.DIRECTORS,
                names = extractDataFromArray(ApiConstants.DIRECTING, cast, 2)
            )
        )
        result.add(
            Department(
                type = ApiConstants.ACTORS,
                names = extractDataFromArray(ApiConstants.ACTING, cast, 2)
            )
        )
        result.add(
            Department(
                type = ApiConstants.MUSIC,
                names = extractDataFromArray(ApiConstants.SOUND, cast, 2)
            )
        )
        return result
    }

    fun onCloseButtonPressed() = viewModelScope.launch {
        movieDetailEventChannel.send(MovieDetailEvent.NavigateToHomeScreen)
    }

    fun onNavigateToSimilarMovie(movie: Movie) = viewModelScope.launch {
        movieDetailEventChannel.send(MovieDetailEvent.NavigateToSimilarMovie(movie))
    }
    sealed class MovieDetailEvent {
        object NavigateToHomeScreen : MovieDetailEvent()
        data class NavigateToSimilarMovie(val movie: Movie) : MovieDetailEvent()
    }

    var data by mutableStateOf(MovieDetailState())

}
