package com.fahrul.moviecatalogue.ui.tv_show

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvShowEntities = viewModel.getTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(11, tvShowEntities.size)
    }
}