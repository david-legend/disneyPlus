package com.davidcobbina.disneyplus.data.remote.api

import com.davidcobbina.disneyplus.BuildConfig

object ApiConstants {
    private const val API_V3 = "3"
    private const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/$API_V3/"
    const val LIST_ID_ENDPOINT = "list/{apiId}?page=1&api_key=$API_KEY"
    const val MOVIE_DETAIL_ENDPOINT = "movie/{movieId}?api_key=$API_KEY&language=en-US"
    const val SIMILAR_MOVIES_ENDPOINT = "movie/{movieId}/recommendations?api_key=$API_KEY&language=en-US&page=1"
    const val MOVIE_CREDITS_ENDPOINT = "movie/{movieId}/credits?api_key=$API_KEY&language=en-US"
    const val MOVIE_TRAILERS_ENDPOINT = "movie/{movieId}/videos?api_key=$API_KEY&language=en-US"


    const val TV_SERIES_DETAIL_ENDPOINT = "tv/{tvId}?api_key=$API_KEY&language=en-US"
    const val SIMILAR_TV_SERIES_ENDPOINT = "tv/{tvId}/recommendations?api_key=$API_KEY&language=en-US&page=1"
    const val TV_SERIES_CREDITS_ENDPOINT = "tv/{tvId}/credits?api_key=$API_KEY&language=en-US"
    const val TV_SERIES_SEASON_DETAIL_ENDPOINT = "tv/{tvId}/season/{seasonNumber}?api_key=$API_KEY&language=en-US"
    const val TV_SERIES_TRAILERS_ENDPOINT = "tv/{tvId}/videos?api_key=$API_KEY&language=en-US"

    const val DIRECTING = "Directing"
    const val ACTING = "Acting"
    const val SOUND = "Sound"
    const val DIRECTORS = "Directors"
    const val ACTORS = "Actors"
    const val MUSIC = "Music"
    const val MEDIA_TYPE_TV  = "tv"
    const val MEDIA_TYPE_MOVIE  = "movie"
}