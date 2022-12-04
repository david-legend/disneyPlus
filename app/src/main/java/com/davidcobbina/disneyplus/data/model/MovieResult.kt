package com.davidcobbina.disneyplus.data.model


import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("items")
    val items: List<Movie>
)