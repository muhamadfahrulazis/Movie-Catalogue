package com.fahrul.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.fahrul.moviecatalogue.data.GenreEntity
import com.fahrul.moviecatalogue.data.MovieEntity
import com.fahrul.moviecatalogue.utils.DataDummy

class DetailMovieViewModel : ViewModel() {
    private lateinit var movieId : String

    fun setSelectedMovie(movieId : String){
        this.movieId = movieId
    }

    fun getMovie() : MovieEntity{
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyMovies()
        for (movieEntity in movieEntities){
            if (movieEntity.movieId == movieId){
                movie = movieEntity
            }
        }
        return movie
    }

    fun getTvShow() : MovieEntity{
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyTvShow()
        for (movieEntity in movieEntities){
            if (movieEntity.movieId == movieId){
                movie = movieEntity
            }
        }
        return movie
    }

    fun getGenresMovie(): List<GenreEntity>{
        val genres = ArrayList<GenreEntity>()
        val genreEntities = DataDummy.generateDummyGenre()
        for (genreId in getMovie().genresId){
            for (genreEntity in genreEntities){
                if (genreEntity.genreId == genreId){
                    genres.add(genreEntity)
                }
            }
        }

        return genres
    }

    fun getGenresTvShow(): List<GenreEntity>{
        val genres = ArrayList<GenreEntity>()
        val genreEntities = DataDummy.generateDummyGenre()
        for (genreId in getTvShow().genresId){
            for (genreEntity in genreEntities){
                if (genreEntity.genreId == genreId){
                    genres.add(genreEntity)
                }
            }
        }

        return genres
    }
}