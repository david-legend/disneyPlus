package com.davidcobbina.disneyplus.data.remote.model


import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("items")
    val items: List<Movie>
)