package com.fahrul.moviecatalogue.ui.movies

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel


    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val movieEntities = viewModel.getMovies()
        assertNotNull(movieEntities)
        assertEquals(11, movieEntities.size)
    }
}