package com.davidcobbina.disneyplus.data.remote.model


import com.google.gson.annotations.SerializedName

data class TrailersResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Trailer>
)