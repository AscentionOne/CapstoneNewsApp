package com.kenchen.capstonenewsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kenchen.capstonenewsapp.repository.NewsRepository
import com.kenchen.capstonenewsapp.views.news.NewsViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `When calling print message actually prints out the message`() {
        val mockRepo = spyk<NewsRepository>()

        every { mockRepo.isDataUsage() } answers {
            flow { }
//            flow { emit(true) }
        }

//        val liveData: LiveData<Boolean> = MutableLiveData<Boolean>(false)

        val viewModel = NewsViewModel(mockRepo)

        assertEquals("This is in test", viewModel.getMessage("This is in test"))


//        coEvery { viewModel.isDataUsageLiveData } returns liveData


//        coEvery { viewModel.isDataUsageLiveData } coAnswers  {
//           val data = flow {
//               emit(true)
//           }
//
//            data.asLiveData()
//        }

//        coEvery { viewModel.isDataUsageLiveData } answers {
//            val data = MutableLiveData<Boolean>()
//            data.value = true
//            data
//        }
//
//        every { viewModel.printMessage(any()) } answers {
//            println("123")
//        }

        verify(exactly = 1) { mockRepo.isDataUsage() }
//        verify(exactly = 1) { viewModel.print("123") }
    }

    @Test
    fun `print message`() {
        val mockRepo = mockk<NewsRepository>()

        every { mockRepo.isDataUsage() } answers {
            flow { }
//            flow { emit(true) }
        }

        val viewModel = NewsViewModel(mockRepo)



//        val mockViewModel = mockk<NewsViewModel>()

//        every { mockViewModel.print(any()) } just Runs

        viewModel.print("1234")

//        mockViewModel.print("123")

        verify(exactly = 1) { viewModel.print("1234") }
//        verify(exactly = 1) { println("1234") }
    }
}

/**
 * Question:
 * 1. how do I test the function that returns Unit (nothing)
 * 2. what is the difference between black-box and white-box testing
 * */
