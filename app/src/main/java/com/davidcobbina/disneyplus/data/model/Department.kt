package com.davidcobbina.disneyplus.data.model

data class Department(
    val type: String,
    var names: List<String> = mutableListOf<String>(),
)
