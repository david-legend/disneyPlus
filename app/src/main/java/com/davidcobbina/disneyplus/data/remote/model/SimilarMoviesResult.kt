package com.davidcobbina.disneyplus.data.remote.model


import com.google.gson.annotations.SerializedName

data class SimilarMoviesResult(
    @SerializedName("results")
    val results: List<Movie>
)