package com.davidcobbina.disneyplus.model

data class DownloadedMovie(
    val movieCover: Int,
    val title: String,
    val yearReleased: String,
    val downloadedSize: String,
    val isSeries: Boolean = false,
    val numberOfEpisodes: Int? = 0
)