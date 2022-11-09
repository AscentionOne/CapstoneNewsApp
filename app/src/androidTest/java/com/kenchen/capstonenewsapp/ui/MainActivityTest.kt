package com.kenchen.capstonenewsapp.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.kenchen.capstonenewsapp.R
import com.kenchen.capstonenewsapp.ui.newslist.NewsListViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    // UI test
    // espresso

    @Test
    fun searchViewIsDisplayed() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
    }

    @Test
    fun goToNewDetailView() {
        onView(withId(R.id.newsListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<NewsListViewHolder>(0, click()))
        onView(withId(R.id.newsDetailView)).check(matches(isDisplayed()))
    }
}
