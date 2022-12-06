package com.davidcobbina.disneyplus.data

import com.davidcobbina.disneyplus.data.api.MoviesAPi
import com.davidcobbina.disneyplus.data.model.*
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesAPi: MoviesAPi
) {

    suspend fun getMovieFeed(): List<Movie> {
        return moviesAPi.getMovieFeed().items
    }

    suspend fun getMovieDetail(movieId: String): MovieDetail {
        return moviesAPi.getMovieDetail(movieId)
    }

    suspend fun getMovieTrailers(movieId: String): List<Trailer> {
        return moviesAPi.getMovieTrailers(movieId).results
    }
    suspend fun getSimilarMovies(movieId: String): List<Movie> {
        return moviesAPi.getSimilarMovies(movieId).results
    }

    suspend fun getTvSeriesDetail(tvId: String): TvSeriesDetail {
        return moviesAPi.getTvSeriesDetail(tvId)
    }

    suspend fun getSimilarTvSeries(tvId: String): List<Movie> {
        return moviesAPi.getSimilarTvSeries(tvId).results
    }

    suspend fun getMovieCredits(movieId: String): MovieCredits {
        return moviesAPi.getMovieCredits(movieId)
    }

    suspend fun getTvSeriesCredits(tvId: String): MovieCredits {
        return moviesAPi.getTvSeriesCredits(tvId)
    }

    suspend fun getTvSeriesSeasonDetail(tvId: String, seasonNumber: String): SeasonDetail {
        return moviesAPi.getTvSeriesSeasonDetail(tvId, seasonNumber)
    }

    suspend fun getTvSeriesTrailers(tvId: String): List<Trailer> {
        return moviesAPi.getTvSeriesTrailers(tvId).results
    }
}