package com.davidcobbina.disneyplus.data.remote.api

import com.davidcobbina.disneyplus.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesAPi {

    @GET(ApiConstants.LIST_ENDPOINT)
    suspend fun getMovieFeed(): MovieResult

    @GET(ApiConstants.MOVIE_DETAIL_ENDPOINT)
    suspend fun getMovieDetail(@Path("movieId") movieId: String): MovieDetail

    @GET(ApiConstants.SIMILAR_MOVIES_ENDPOINT)
    suspend fun getSimilarMovies(@Path("movieId") movieId: String): SimilarMoviesResult

    @GET(ApiConstants.MOVIE_CREDITS_ENDPOINT)
    suspend fun getMovieCredits(@Path("movieId") movieId: String): MovieCredits

    @GET(ApiConstants.MOVIE_TRAILERS_ENDPOINT)
    suspend fun getMovieTrailers(
        @Path("movieId") movieId: String
    ): TrailersResult


    @GET(ApiConstants.TV_SERIES_DETAIL_ENDPOINT)
    suspend fun getTvSeriesDetail(@Path("tvId") tvId: String): TvSeriesDetail

    @GET(ApiConstants.SIMILAR_TV_SERIES_ENDPOINT)
    suspend fun getSimilarTvSeries(@Path("tvId") tvId: String): SimilarMoviesResult

    @GET(ApiConstants.TV_SERIES_CREDITS_ENDPOINT)
    suspend fun getTvSeriesCredits(@Path("tvId") tvId: String): MovieCredits

    @GET(ApiConstants.TV_SERIES_SEASON_DETAIL_ENDPOINT)
    suspend fun getTvSeriesSeasonDetail(
        @Path("tvId") tvId: String,
        @Path("seasonNumber") seasonNumber: String
    ): SeasonDetail

    @GET(ApiConstants.TV_SERIES_TRAILERS_ENDPOINT)
    suspend fun getTvSeriesTrailers(
        @Path("tvId") tvId: String
    ): TrailersResult
}