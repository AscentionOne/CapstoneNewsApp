package com.kenchen.capstonenewsapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.kenchen.capstonenewsapp.ui.MainActivity
import com.kenchen.capstonenewsapp.ui.newslist.NewsListViewHolder
import org.junit.Rule
import org.junit.Test

class MyComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    // val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun goToNewsDetailViewCheckComposeTitle() {
        // got to NewsDetailView, since the target Compose is in NewsDetailView
        Espresso.onView(ViewMatchers.withId(R.id.newsListRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<NewsListViewHolder>(0,
                ViewActions.click()))

        val titleContentDescription = composeTestRule.activity.getString(R.string.news_title_description)
        // Check whether it exist through content description
        composeTestRule.onNodeWithContentDescription(titleContentDescription).assertIsDisplayed()

        Espresso.onView(ViewMatchers.withId(R.id.newsDetailView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
