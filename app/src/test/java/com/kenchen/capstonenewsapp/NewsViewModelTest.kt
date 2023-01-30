package com.kenchen.capstonenewsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.model.ArticleState
import com.kenchen.capstonenewsapp.model.Source
import com.kenchen.capstonenewsapp.repository.NewsRepository
import com.kenchen.capstonenewsapp.ui.newslist.NewsViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {

    @MockK
    lateinit var mockRepository: NewsRepository

    lateinit var viewModel: NewsViewModel

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

//    @ExperimentalCoroutinesApi
//    @get:Rule
//    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        // FIXME: can we put every in set up before test start?
//        every { mockRepository.isDataUsage() } returns flow { emit(true) }

//        viewModel = NewsViewModel(mockRepository)
    }

    @Test
    fun `fetch articles`() {

        val mockObserver = mockk<Observer<ArticleState>>()

        val expectedResult = ArticleState.Ready(listOf(dummyArticle))

        every { mockRepository.isDataUsage() } returns flow { emit(true) }

        every { mockRepository.getArticles() } returns flow { emit(expectedResult) }

        var onChangedInvoked = 0


        val viewModel = NewsViewModel(mockRepository)

        viewModel.headLineNewsLiveData.observeForever(mockObserver)

        every { mockObserver.onChanged(expectedResult) } answers {
            onChangedInvoked++
        }

//        viewModel.fetchArticle()

//        verify(exactly = 1) { mockRepository.getArticles() }

//        Assert.assertEquals(1, onChangedInvoked)
        verify { mockObserver.onChanged(expectedResult) }
    }

    @Test
    fun `search articles`() {

        every { mockRepository.isDataUsage() } returns flow { emit(true) }

        val viewModel = NewsViewModel(mockRepository)

        viewModel.searchArticles("test")

        coVerify(exactly = 1) { mockRepository.searchArticles(any()) }
    }

    @Test
    fun `toggle data usage`() {
        every { mockRepository.isDataUsage() } returns flow { emit(true) }

        val viewModel = NewsViewModel(mockRepository)

        viewModel.toggleDataUsage()

        coVerify(exactly = 1) { mockRepository.toggleDataUsage() }
    }
}

/**
 * Question:
 * 1. how do I test the function that returns Unit (nothing) V
 * 2. what is the difference between black-box and white-box testing V
 * 3. can we put all the code in runBlocking ? V
 * 4. Do we need CoroutinesTestRule?
 * */
