package com.davidcobbina.disneyplus.data.remote.model


import com.google.gson.annotations.SerializedName

data class MovieCredits(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Cast>,
    @SerializedName("id")
    val id: Int,
    var primaryCast : List<Department> = mutableListOf(),
)