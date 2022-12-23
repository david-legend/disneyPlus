package com.davidcobbina.disneyplus.data.remote.model

data class Department(
    val type: String,
    var names: List<String> = mutableListOf<String>(),
)
