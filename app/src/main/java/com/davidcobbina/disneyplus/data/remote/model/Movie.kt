package com.davidcobbina.disneyplus.data.remote.model


import android.net.Uri
import com.davidcobbina.disneyplus.data.remote.api.ApiConstants
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("original_name")
    val originalName: String = "",
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
) {
    fun getMovieTitle(): String {
        return if (mediaType == ApiConstants.MEDIA_TYPE_TV) originalName else originalTitle
    }

    override fun toString(): String = Uri.encode(Gson().toJson(this))
}