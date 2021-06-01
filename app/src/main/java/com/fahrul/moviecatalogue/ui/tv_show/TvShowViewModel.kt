package com.fahrul.moviecatalogue.ui.tv_show

import androidx.lifecycle.ViewModel
import com.fahrul.moviecatalogue.data.MovieEntity
import com.fahrul.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel(){
    fun getTvShow() : List<MovieEntity> = DataDummy.generateDummyTvShow()
}