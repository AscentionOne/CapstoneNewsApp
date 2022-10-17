package com.kenchen.capstonenewsapp.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kenchen.capstonenewsapp.database.NewsDatabase
import com.kenchen.capstonenewsapp.database.dao.ArticleDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var newsDatabase: NewsDatabase
    private lateinit var articleDao: ArticleDao

    @Before
    fun initDatabase() {
        // Information stored in an in-memory database disappears when the tests finish
        newsDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsDatabase::class.java
        ).build()

        articleDao = newsDatabase.articleDao()
    }

    @After
    fun closeDatabase() {
        newsDatabase.close()
    }

    @Test
    fun `getArticlesReturnsEmptyList`() {
        runBlocking {
            val articles = articleDao.getArticles()

            assertEquals(0, articles.size)
        }
    }
}
