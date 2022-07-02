package com.davidcobbina.disneyplus.data

import com.davidcobbina.disneyplus.R

data class MovieData( val movieCover: Int)

var suggestedMovieList = List(15) { MovieData(R.drawable.mandalorian) }

//fun generateSuggestionListData() {
//    for (i in 1..15) movie.add(MovieData(R.drawable.mandalorian))
//}