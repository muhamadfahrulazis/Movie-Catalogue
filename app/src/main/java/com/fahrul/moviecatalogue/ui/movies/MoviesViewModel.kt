package com.fahrul.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import com.fahrul.moviecatalogue.data.MovieEntity
import com.fahrul.moviecatalogue.utils.DataDummy

class MoviesViewModel : ViewModel() {
    fun getMovies() : List<MovieEntity> =  DataDummy.generateDummyMovies()
}