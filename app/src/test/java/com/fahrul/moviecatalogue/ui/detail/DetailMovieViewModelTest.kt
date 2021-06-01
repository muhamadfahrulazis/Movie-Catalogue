package com.fahrul.moviecatalogue.ui.detail

import com.fahrul.moviecatalogue.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val dummyMoviesTvShow = DataDummy.generateDummyTvShow()[0]
    private val movieId = dummyMovies.movieId

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }


    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovies.movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovies.movieId, movieEntity.movieId)
        assertEquals(dummyMovies.title, movieEntity.title)
        assertEquals(dummyMovies.description, movieEntity.description)
        assertEquals(dummyMovies.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovies.rating.toString(), movieEntity.rating.toString())
        assertEquals(dummyMovies.backdropPath, movieEntity.backdropPath)
        assertEquals(dummyMovies.released, movieEntity.released)
        assertEquals(dummyMovies.trailerPath, movieEntity.trailerPath)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedMovie(dummyMoviesTvShow.movieId)
        val movieEntity = viewModel.getTvShow()
        assertNotNull(movieEntity)
        assertEquals(dummyMoviesTvShow.movieId, movieEntity.movieId)
        assertEquals(dummyMoviesTvShow.title, movieEntity.title)
        assertEquals(dummyMoviesTvShow.description, movieEntity.description)
        assertEquals(dummyMoviesTvShow.posterPath, movieEntity.posterPath)
        assertEquals(dummyMoviesTvShow.rating.toString(), movieEntity.rating.toString())
        assertEquals(dummyMoviesTvShow.backdropPath, movieEntity.backdropPath)
        assertEquals(dummyMoviesTvShow.released, movieEntity.released)
        assertEquals(dummyMoviesTvShow.trailerPath, movieEntity.trailerPath)
    }

    @Test
    fun getGenresMovie() {
        viewModel.setSelectedMovie(dummyMovies.movieId)
        val genreEntities = viewModel.getGenresMovie()
        assertNotNull(genreEntities)
        assertEquals(2, genreEntities.size)
    }

    @Test
    fun getGenresTvShow() {
        viewModel.setSelectedMovie(dummyMoviesTvShow.movieId)
        val genreEntities = viewModel.getGenresTvShow()
        assertNotNull(genreEntities)
        assertEquals(4, genreEntities.size)
    }
}