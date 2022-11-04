package com.davidcobbina.disneyplus.ui.screens.downloads_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.model.DownloadedMovie




data class DownloadsViewState(
    val downloadedMovies: List<DownloadedMovie> = List(15) {
        DownloadedMovie(
            movieCover = R.drawable.mandalorian,
            title = "The Mandalorian",
            yearReleased = "2019",
            downloadedSize = "2.7Gb"
        )
    }
)
class DownloadsViewModel() : ViewModel() {
    var data by mutableStateOf(DownloadsViewState())
}