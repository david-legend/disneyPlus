package com.davidcobbina.disneyplus.ui.screens.tv_series_detail_screen


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.MoviesRepository
import com.davidcobbina.disneyplus.data.api.ApiConstants
import com.davidcobbina.disneyplus.data.model.*
import com.davidcobbina.disneyplus.model.ActionList
import com.davidcobbina.disneyplus.model.DisneyMovie
import com.davidcobbina.disneyplus.model.Episode
import com.davidcobbina.disneyplus.util.extractDataFromArray
import com.davidcobbina.disneyplus.util.parseYearFromDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TvSeriesDetailState(

    val suggestedMovieList: List<DisneyMovie> = List(15) { DisneyMovie(R.drawable.mandalorian) },
    val trailers: List<DisneyMovie> = List(5) {
        DisneyMovie(R.drawable.mandalorian_sunny)
    },
    val actionList: List<ActionList> = arrayListOf(
        ActionList("Download", R.drawable.ic_arrow_down),
        ActionList("Share", R.drawable.ic_share),
        ActionList("More Like This", R.drawable.ic_search),
        ActionList("Cast on TV", R.drawable.ic_cast),
    ),
    val seasonsList: List<String> = arrayListOf(
        "Season 1",
        "Season 2",
        "Season 3",
    ),
    val episodes: List<Episode> = arrayListOf(
        Episode(
            title = "The Mandalorian",
            "Episode 1",
            duration = "39m",
            description = R.string.lorem_ipsum,
            isDownloaded = true
        ),
        Episode(
            title = "The Mandalorian",
            "Episode 2",
            duration = "39m",
            description = R.string.lorem_ipsum,
        ), Episode(
            title = "The Mandalorian",
            "Episode 3",
            duration = "39m",
            description = R.string.lorem_ipsum,
            isDownloaded = true
        ),
    )
)

@HiltViewModel
class TvSeriesDetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val tvSeriesDetailEventChannel = Channel<TvSeriesDetailEvent>()
    val tvSeriesDetailEvent = tvSeriesDetailEventChannel.receiveAsFlow()

    // Fetching TV Series Detail Loader
    private val _tvSeriesDetailLoading = MutableStateFlow(false)
    val tvSeriesDetailLoading: StateFlow<Boolean>
        get() = _tvSeriesDetailLoading

    // Fetching TV Series Detail
    private val _tvSeriesDetail = MutableStateFlow<TvSeriesDetail?>(null)
    val tvSeriesDetail: StateFlow<TvSeriesDetail?>
        get() = _tvSeriesDetail

    // Fetching TV Series Season Detail Loader
    private val _seasonDetailLoading = MutableStateFlow(false)
    val seasonDetailLoading: StateFlow<Boolean>
        get() = _seasonDetailLoading
    // Fetching TV Series Season Detail
    private val _seasonDetail = MutableStateFlow<SeasonDetail?>(null)
    val seasonDetail: StateFlow<SeasonDetail?>
        get() = _seasonDetail
    // Current Season Number on Detail Page
    private val _seasonNumber = MutableStateFlow(1)
    val seasonNumber: StateFlow<Int>
        get() = _seasonNumber

    // Fetching TV Series Similar Series Loader
    private val _similarMoviesLoading = MutableStateFlow(false)
    val similarMoviesLoading: StateFlow<Boolean>
        get() = _similarMoviesLoading
    // Fetching TV Series Similar Series
    private val _similarMovies = MutableStateFlow(emptyList<Movie>())
    val similarMovies: StateFlow<List<Movie>>
        get() = _similarMovies

    // Fetching TV Series Credits
    private val _credits = MutableStateFlow<MovieCredits?>(null)
    val credits: StateFlow<MovieCredits?>
        get() = _credits

    fun getTvSeriesDetail(tvId: String) {
        viewModelScope.launch {
            _tvSeriesDetailLoading.value = true
            val detail = moviesRepository.getTvSeriesDetail(tvId)
            detail.metaData = processMetaData(detail)
            _tvSeriesDetail.value = detail
            _tvSeriesDetailLoading.value = false

        }
    }

    fun getTvSeriesSeasonDetail(tvId: String, seasonNumber: String) {
        viewModelScope.launch {
            _seasonDetailLoading.value = true
            val detail = moviesRepository.getTvSeriesSeasonDetail(tvId, seasonNumber)
            _seasonDetail.value = detail
            _seasonDetailLoading.value = false

        }
    }

    fun getRecommendations(movieId: String) {
        viewModelScope.launch {
            _similarMoviesLoading.value = true
            val similarMovies = moviesRepository.getSimilarTvSeries(movieId)
            _similarMovies.value = similarMovies
            _similarMoviesLoading.value = false

        }
    }


    fun getMovieCredits(movieId: String) {
        viewModelScope.launch {
            val credits = moviesRepository.getTvSeriesCredits(movieId)
            credits.primaryCast = processCast(credits.crew)

            _credits.value = credits
        }
    }

    fun onCloseButtonPressed() = viewModelScope.launch {
        tvSeriesDetailEventChannel.send(TvSeriesDetailEvent.NavigateToHomeScreen)
    }

    private fun processMetaData(detail: TvSeriesDetail): List<String> {
        val metaData = mutableListOf<String>()
        metaData.add(parseYearFromDate(detail.firstAirDate))
        metaData.add("${detail.numberOfSeasons} Season${if (detail.numberOfSeasons > 1) "s" else ""}")
        metaData.add("CC")
        metaData.add("4K")
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



    fun onSeasonChange() = viewModelScope.launch {
        tvSeriesDetailEventChannel.send(TvSeriesDetailEvent.ChangeSeason)
    }

    sealed class TvSeriesDetailEvent {
        object NavigateToHomeScreen : TvSeriesDetailEvent()
        object ChangeSeason : TvSeriesDetailEvent()
    }

    var data by mutableStateOf(TvSeriesDetailState())

}
