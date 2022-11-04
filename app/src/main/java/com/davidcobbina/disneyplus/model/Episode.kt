package com.davidcobbina.disneyplus.model

data class Episode(
    val title: String,
    val episodeNumber: String,
    val duration: String,
    val description: Int,
    val isDownloaded: Boolean = false
)