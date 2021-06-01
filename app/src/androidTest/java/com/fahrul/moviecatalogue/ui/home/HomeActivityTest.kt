package com.fahrul.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.fahrul.moviecatalogue.R
import com.fahrul.moviecatalogue.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShow()

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadMoviesTvShow(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailMovies(){
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.text_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc)).check(matches(withText(dummyMovies[0].description)))
        onView(withId(R.id.btn_trailer)).perform(click())
        onView(withId(R.id.web_view)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMoviesTvShow(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.text_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.text_desc)).check(matches(withText(dummyTvShow[0].description)))
        onView(withId(R.id.btn_trailer)).perform(click())
        onView(withId(R.id.web_view)).check(matches(isDisplayed()))
    }

    @Test
    fun loadGenres(){
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.rv_genres)).check(matches(isDisplayed()))
    }

}