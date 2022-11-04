package com.davidcobbina.disneyplus.ui.screens.movie_detail_screen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.model.ActionList
import com.davidcobbina.disneyplus.model.DisneyMovie
import com.davidcobbina.disneyplus.model.Episode

data class MovieDetailState(

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
        Episode(
            title = "The Mandalorian",
            "Episode 4",
            duration = "39m",
            description = R.string.lorem_ipsum,
        ),

        Episode(
            title = "The Mandalorian",
            "Episode 5",
            duration = "39m",
            description = R.string.lorem_ipsum,
        ),
        Episode(
            title = "The Mandalorian",
            "Episode 6",
            duration = "39m",
            description = R.string.lorem_ipsum,
        )
    )
)


class MovieDetailViewModel() : ViewModel() {

    var data by mutableStateOf(MovieDetailState())
}
