package com.fahrul.moviecatalogue.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fahrul.moviecatalogue.R
import com.fahrul.moviecatalogue.ui.movies.MoviesFragment
import com.fahrul.moviecatalogue.ui.tv_show.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_show)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> MoviesFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])
}