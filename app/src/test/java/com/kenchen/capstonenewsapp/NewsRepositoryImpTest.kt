package com.kenchen.capstonenewsapp

import android.util.Log
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
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.*
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
        mockkStatic(Log::class)
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `When articles are searched through title return list of matched articles`() {
        val expectedResult = 1

        coEvery { mockArticleDao.searchArticles(any()) } returns listOf(dummyArticle)


        runBlocking {
            val articles = newsRepository.searchArticles("test")

            assertEquals(expectedResult, articles.size)
        }
    }

    @Test
    fun `When articles are searched through title and no articles are matched return empty list`() {
        val expectedResult = 0

        coEvery { mockArticleDao.searchArticles(any()) } returns listOf()

        runBlocking {
            val articles = newsRepository.searchArticles("US")

            assertEquals(expectedResult, articles.size)
        }
    }

    @Test
    fun `toggle data usage`() {
        runBlocking {
            coEvery { mockPrefsStore.toggleDataUsage() } just runs

            newsRepository.toggleDataUsage()

            coVerify(exactly = 1) { mockPrefsStore.toggleDataUsage() }
        }
    }

    @Test
    fun `is data usage should returns flow of boolean`() {
        val expectedResult = true

        runBlocking {
            coEvery { mockPrefsStore.isDataUsage() } returns flow { emit(true) }

            val actualResult = newsRepository.isDataUsage().first()

            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `articles are fetched from database first and ArticleState ready with articles is returned`() {
        val expectedResult = ArticleState.Ready(listOf(dummyArticle))

        coEvery { mockArticleDao.getArticles() } returns listOf(dummyArticle)

        runBlocking {
            val actualResult = newsRepository.getArticles().first()
            assertEquals(expectedResult, actualResult)
        }
    }

//    @Test
//    fun `fetch with wifi enabled, remote api success ArticleState ready is returned`() {
//        val expectedException = Exception("error message")
//        every { mockPrefsStore.isDataUsage() } returns flow { emit(false) }
//        every { mockNetworkStatusChecker.isOnWifiConnection() } returns true
//        coEvery { mockRemoteApi.getTopHeadlinesByCountry("us") } returns listOf(dummyArticle)
//        coEvery { mockArticleDao.getArticles() } returns listOf(dummyArticle)
//
//        val flowArticleState = newsRepository.getArticles()
//
//        runBlocking {
//            assertEquals(ArticleState.Ready(listOf(dummyArticle)),
//                flowArticleState.drop(1).first()
//            )
//        }
//    }

    @Test
    fun `fetch with wifi enabled, remote api success return ArticleState partial with articles`() {
        coEvery { mockArticleDao.getArticles() } returns listOf(dummyArticle)
        every { mockPrefsStore.isDataUsage() } returns flow { emit(true) }
        every { mockNetworkStatusChecker.isOnWifiConnection() } returns true
        coEvery { mockRemoteApi.getTopHeadlinesByCountry("us") } returns listOf(dummyArticle)

        coEvery { mockArticleDao.clearArticles() } just runs
        coEvery { mockArticleDao.addArticles(any()) } just runs

        runBlocking {
            val flowArticleState = newsRepository.getArticles()

//            println(flowArticleState.take(3).toList())
            val articleStates = flowArticleState.toList()
            println(flowArticleState.count()) // 2

            val articleStateReady = ArticleState.Ready(listOf(dummyArticle))

            assertEquals(listOf(articleStateReady, articleStateReady),
                articleStates
            )

//            assertEquals(ArticleState.Ready(listOf(dummyArticle)),
//                flowArticleState.drop(1).first()
//            )
//            assertEquals(2, flowArticleState.count())
        }
    }

    @Test
    fun `fetch with wifi enabled, remote api error return ArticleState partial with articles`() {
        val expectedException = Exception("error message")
        coEvery { mockArticleDao.getArticles() } returns listOf(dummyArticle)
        every { mockPrefsStore.isDataUsage() } returns flow { emit(false) }
        every { mockNetworkStatusChecker.isOnWifiConnection() } returns true
        coEvery { mockRemoteApi.getTopHeadlinesByCountry(any()) } throws expectedException

        runBlocking {
            val flowArticleState = newsRepository.getArticles()

            assertEquals(ArticleState.Partial(listOf(dummyArticle), RemoteError
                .UnexpectedException("Unexpected Error")),
                flowArticleState.drop(1).first()
            )
        }

    }

}
