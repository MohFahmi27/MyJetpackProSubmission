package com.mfahmi.myjetpackprosubmission.ui.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mfahmi.myjetpackprosubmission.R
import com.mfahmi.myjetpackprosubmission.repositories.MoviesRepository
import com.mfahmi.myjetpackprosubmission.repositories.TvShowRepository
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewVisibility() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
    }

    @Test
    fun testDetailViewDataMovie() {
        val getMovieList = MoviesRepository.getMoviesData()
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_toolbar_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_toolbar_name)).check(matches(withText(getMovieList[0].movieTitle)))
        onView(withId(R.id.tv_title_detail)).check(matches(withText(getMovieList[0].movieTitle)))
        onView(withId(R.id.tv_release_date)).check(matches(withText(getMovieList[0].movieReleaseDate)))
        onView(withId(R.id.tv_rating_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun testDetailViewDataTvShow() {
        val getTvShowList = TvShowRepository.getTvShowData()
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_toolbar_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_toolbar_name)).check(matches(withText(getTvShowList[0].tvShowTitle)))
        onView(withId(R.id.tv_title_detail)).check(matches(withText(getTvShowList[0].tvShowTitle)))
        onView(withId(R.id.tv_release_date)).check(matches(withText(getTvShowList[0].tvShowReleaseDate)))
        onView(withId(R.id.tv_rating_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun testDetailBackButtonMovie() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_back_detail)).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
    }

    @Test
    fun testDetailBackButtonTvShow() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.btn_back_detail)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
    }

}