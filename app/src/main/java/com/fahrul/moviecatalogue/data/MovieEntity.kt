package com.fahrul.moviecatalogue.data

data class MovieEntity(
    var movieId: String,
    var title : String,
    var description: String,
    var released: String,
    var rating: Double,
    var posterPath: String,
    var backdropPath: String,
    var favorited: Boolean = false,
    var genresId: List<String>,
    var trailerPath: String
)