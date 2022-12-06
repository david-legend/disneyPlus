package com.davidcobbina.disneyplus.data.model


import com.google.gson.annotations.SerializedName

data class TrailersResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Trailer>
)