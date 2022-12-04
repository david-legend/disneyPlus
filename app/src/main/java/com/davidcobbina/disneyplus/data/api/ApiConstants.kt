package com.davidcobbina.disneyplus.data.api

object ApiConstants {
    private const val API_V3 = "3"
    const val BASE_URL = "https://api.themoviedb.org/$API_V3/"
    const val LIST_ENDPOINT = "list/8229678?page=1&api_key=8b29c6e37e426fa616c3805ea363200a"
    const val MOVIE_DETAIL_ENDPOINT = "movie/{movieId}?api_key=8b29c6e37e426fa616c3805ea363200a&language=en-US"
    const val SIMILAR_MOVIES_ENDPOINT = "movie/{movieId}/recommendations?api_key=8b29c6e37e426fa616c3805ea363200a&language=en-US&page=1"
    const val MOVIE_CREDITS_ENDPOINT = "movie/{movieId}/credits?api_key=8b29c6e37e426fa616c3805ea363200a&language=en-US"


    const val TV_SERIES_DETAIL_ENDPOINT = "tv/{tvId}?api_key=8b29c6e37e426fa616c3805ea363200a&language=en-US"
    const val SIMILAR_TV_SERIES_ENDPOINT = "tv/{tvId}/recommendations?api_key=8b29c6e37e426fa616c3805ea363200a&language=en-US&page=1"
    const val TV_SERIES_CREDITS_ENDPOINT = "tv/{tvId}/credits?api_key=8b29c6e37e426fa616c3805ea363200a&language=en-US"
    const val TV_SERIES_SEASON_DETAIL_ENDPOINT = "tv/{tvId}/season/{seasonNumber}?api_key=8b29c6e37e426fa616c3805ea363200a&language=en-US"

    const val DIRECTING = "Directing"
    const val ACTING = "Acting"
    const val SOUND = "Sound"
    const val DIRECTORS = "Directors"
    const val ACTORS = "Actors"
    const val MUSIC = "Music"
    const val MEDIA_TYPE_TV  = "tv"
    const val MEDIA_TYPE_MOVIE  = "movie"
}