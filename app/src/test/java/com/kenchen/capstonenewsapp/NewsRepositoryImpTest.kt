package com.kenchen.capstonenewsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kenchen.capstonenewsapp.database.dao.ArticleDao
import com.kenchen.capstonenewsapp.database.dao.SourceDao
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.model.ArticleState
import com.kenchen.capstonenewsapp.model.Source
import com.kenchen.capstonenewsapp.networking.NetworkStatusChecker
import com.kenchen.capstonenewsapp.networking.RemoteApi
import com.kenchen.capstonenewsapp.networking.RemoteError
import com.kenchen.capstonenewsapp.prefsstore.PrefsStore
import com.kenchen.capstonenewsapp.repository.NewsRepositoryImp
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsRepositoryImpTest {

    @MockK
    lateinit var mockArticleDao: ArticleDao

    @MockK
    lateinit var mockSourceDao: SourceDao

    @MockK
    lateinit var mockRemoteApi: RemoteApi

    @MockK
    lateinit var mockPrefsStore: PrefsStore

    @MockK
    lateinit var mockNetworkStatusChecker: NetworkStatusChecker

    private lateinit var newsRepository: NewsRepositoryImp

    private val dummyArticle = Article(
        Source(name = "CNN"),
        "Ken",
        "Test",
        "This is a test article",
        "www.test.com",
        "www.test.com",
        "CNN",
        "Show me how to test.",
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        newsRepository = NewsRepositoryImp(
            mockArticleDao,
            mockSourceDao,
            mockRemoteApi,
            mockPrefsStore,
            mockNetworkStatusChecker
        )
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `When articles are searched through title return list of matched articles`() {

        coEvery { mockArticleDao.searchArticles(any()) } answers {
            listOf(dummyArticle)
        }

        runBlocking {
            val articles = newsRepository.searchArticles("test")

            assertEquals(1, articles.size)
        }
    }

    @Test
    fun `When articles are searched through title and no articles are matched return empty list`() {

        coEvery { mockArticleDao.searchArticles(any()) } answers {
            listOf()
        }

        runBlocking {
            val articles = newsRepository.searchArticles("US")

            assertEquals(0, articles.size)
        }
    }

    @Test
    fun `when articles are fetched flow of ArticleState ready with articles is returned`() {

        val flowArticleState = newsRepository.getArticles()

        coEvery { mockArticleDao.getArticles() } returns listOf(dummyArticle)

        runBlocking {
            assertEquals(ArticleState.Ready(listOf(dummyArticle)), flowArticleState.first())
        }

    }

    @Test
    fun `when articles are fetched flow of ArticleState partial with articles is returned`() {

        val flowArticleState = newsRepository.getArticles()

        coEvery { mockArticleDao.getArticles() } returns listOf(dummyArticle)

        runBlocking {
            assertEquals(ArticleState.Partial(listOf(dummyArticle), RemoteError.ApiException
                ("error")),
                flowArticleState.first())
        }

    }

}
