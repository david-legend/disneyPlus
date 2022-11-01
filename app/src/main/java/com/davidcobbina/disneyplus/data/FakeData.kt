package com.davidcobbina.disneyplus.data

import androidx.compose.ui.graphics.Color
import com.davidcobbina.disneyplus.R

data class UserAccount(val avatar: Int, val username: String)
data class DisneyMovie(val movieCover: Int)
data class AvatarProfile(val avatar: Int)
data class FranchiseStudio(val title: Int, val logo: Int, val color: Color? = null)
data class NavItem(val title: Int, val icon: Int, val isSelected: Boolean = false)
data class SettingsListItem(
    val title: Int,
    val subtitle: Int,
    val leadingIcon: Int,
    val trailingIcon: Int
)

data class AvatarCategory(val title: Int, val isSelected: Boolean = false)
data class DownloadedMovie(
    val movieCover: Int,
    val title: String,
    val yearReleased: String,
    val downloadedSize: String,
    val isSeries: Boolean = false,
    val numberOfEpisodes: Int? = 0
)

data class ActionList(val title: String, val icon: Int)
data class EpisodeItem(
    val title: String,
    val episodeNumber: String,
    val duration: String,
    val description: Int,
    val isDownloaded: Boolean = false
)


var userAccounts = arrayListOf(
    UserAccount(R.drawable.merida, "Merida"),
    UserAccount(R.drawable.moana, "Moana"),
    UserAccount(R.drawable.olaf, "Olaf"),

    )
var suggestedMovieList = List(15) { DisneyMovie(R.drawable.mandalorian) }
var avatarProfilesList = arrayListOf(
    AvatarProfile(R.drawable.merida),
    AvatarProfile(R.drawable.moana),
    AvatarProfile(R.drawable.olaf),
    AvatarProfile(R.drawable.mr_incredible),
    AvatarProfile(R.drawable.mushu),
    AvatarProfile(R.drawable.simba),
)


// Navigation List
var navItemsList = arrayListOf(
    NavItem(R.string.everything, R.drawable.ic_layers, true),
    NavItem(R.string.movies, R.drawable.ic_film),
    NavItem(R.string.shows, R.drawable.ic_monitor),
    NavItem(R.string.watchlist, R.drawable.ic_check),
    NavItem(R.string.downloads, R.drawable.ic_arrow_downward),
)

var franchiseStudioList = arrayListOf(
    FranchiseStudio(R.string.star_wars, R.drawable.star_wars, color = Color.White),
    FranchiseStudio(R.string.marvel, R.drawable.marvel_logo),
    FranchiseStudio(R.string.disney, R.drawable.walt_disney),
    FranchiseStudio(R.string.pixar, R.drawable.pixar, color = Color.White),
    FranchiseStudio(R.string.national_geographic, R.drawable.national_geograhic),
)


var avatarCategories = arrayListOf(
    AvatarCategory(R.string.princess, isSelected = true),
    AvatarCategory(R.string.hero),
    AvatarCategory(R.string.villain),
    AvatarCategory(R.string.buddy),
)

var downloadedMovies = List(15) {
    DownloadedMovie(
        movieCover = R.drawable.mandalorian,
        title = "The Mandalorian",
        yearReleased = "2019",
        downloadedSize = "2.7Gb"
    )
}

var movieDetailCover = arrayListOf(
    DisneyMovie(R.drawable.mandalorian_sunny),
)

var trailers = List(5) {
    DisneyMovie(R.drawable.mandalorian_sunny)
}

var actionsList = arrayListOf(
    ActionList("Download", R.drawable.ic_arrow_down),
    ActionList("Share", R.drawable.ic_share),
    ActionList("More Like This", R.drawable.ic_search),
    ActionList("Cast on TV", R.drawable.ic_cast),
)

var seasonsList = arrayListOf(
    "Season 1",
    "Season 2",
    "Season 3",
)
var episodes = arrayListOf(
    EpisodeItem(
        title = "The Mandalorian",
        "Episode 1",
        duration = "39m",
        description = R.string.lorem_ipsum,
        isDownloaded = true
    ),
    EpisodeItem(
        title = "The Mandalorian",
        "Episode 2",
        duration = "39m",
        description = R.string.lorem_ipsum,
    ), EpisodeItem(
        title = "The Mandalorian",
        "Episode 3",
        duration = "39m",
        description = R.string.lorem_ipsum,
        isDownloaded = true
    ),
    EpisodeItem(
        title = "The Mandalorian",
        "Episode 4",
        duration = "39m",
        description = R.string.lorem_ipsum,
    ),

    EpisodeItem(
        title = "The Mandalorian",
        "Episode 5",
        duration = "39m",
        description = R.string.lorem_ipsum,
    ),
    EpisodeItem(
        title = "The Mandalorian",
        "Episode 6",
        duration = "39m",
        description = R.string.lorem_ipsum,
    )

)

var settingsList = arrayListOf(
    SettingsListItem(
        title = R.string.maturity_rating_title,
        subtitle = R.string.maturity_rating_subtitle,
        leadingIcon = R.drawable.ic_alert_octagon,
        trailingIcon = R.drawable.ic_chevron_right,
    ),
    SettingsListItem(
        title = R.string.display_language_title,
        subtitle = R.string.display_language_subtitle,
        leadingIcon = R.drawable.ic_baseline_language,
        trailingIcon = R.drawable.ic_chevron_right,
    ),
    SettingsListItem(
        title = R.string.audios_and_subtitles_title,
        subtitle = R.string.audios_and_subtitles_subtitle,
        leadingIcon = R.drawable.ic_baseline_subtitles,
        trailingIcon = R.drawable.ic_chevron_right,
    )
)