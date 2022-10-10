package com.kenchen.capstonenewsapp.views.news

import androidx.lifecycle.*
import com.kenchen.capstonenewsapp.App.Companion.remoteApi
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.networking.RemoteResult
import com.kenchen.capstonenewsapp.repository.NewsRepository
import com.kenchen.capstonenewsapp.utils.mapException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepo: NewsRepository,
) : ViewModel() {

    class Factory(
        private val newsRepo: NewsRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsViewModel(newsRepo) as T
        }
    }

    private val _headLineNewsLiveData = MutableLiveData<RemoteResult<List<Article>>>()
    val headLineNewsLiveData: LiveData<RemoteResult<List<Article>>> = _headLineNewsLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepo.getArticles().onEach { articles ->
                _headLineNewsLiveData.postValue(articles)
            }.collect()
        }
    }

    // get news articles liveData from Flow
//    val headLineNewsLiveData: LiveData<RemoteResult<List<Article>>> = newsRepo
//        .getArticles().asLiveData()

    fun searchArticles(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val filteredArticle = newsRepo.searchArticles("%$search%")
            _headLineNewsLiveData.postValue(RemoteResult.Success(filteredArticle))
        }
    }

    fun toggleDataUsage() {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepo.toggleDataUsage()
        }

    }

    private var job: Job? = null

    private val _newsLoadingStateLiveData = MutableLiveData<NewsLoadingState>()
    val newsLoadingStateLiveData: LiveData<NewsLoadingState> = _newsLoadingStateLiveData

    fun onActivityReady() {
        // prevent refetching data when orientation changes (save resource)
        if (_headLineNewsLiveData.value == null) {
            getTopHeadlinesNewsByCountry()
        }
    }

    fun refreshNews() {
        getTopHeadlinesNewsByCountry()
    }

    private fun getTopHeadlinesNewsByCountry() {
        _newsLoadingStateLiveData.value = NewsLoadingState.LOADING
        // prevent the job from running multiple time, ex: when network is bad
        // ans user rotate the screen.
        // _headLineNewsLiveData.value is still null so getTopHeadlinesNewsByCountry() will be
        // execute again
        if (job?.isActive == true)
            return
        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = remoteApi.getTopHeadlinesByCountry("us")

                // use postValue instead of value because
                // we are on IO dispatcher and not on the
                // Main (UI) thread
                _headLineNewsLiveData.postValue(RemoteResult.Success(result))
                _newsLoadingStateLiveData.postValue(NewsLoadingState.LOADED)

                /**
                 * option 2:
                 * switch the coroutine context back to the Main dispatcher.
                 * Use value instead of postValue():
                 * withContext(Dispatchers.Main) {
                 * _headlineNewsLIveData.value = RemoteResult.Success(result)
                 * _newsLoadingStateLiveData.value = NewsLoadingState.LOADED
                }
                 * */
            } catch (error: Exception) {
                _newsLoadingStateLiveData.postValue(NewsLoadingState.ERROR)
                _headLineNewsLiveData.postValue(RemoteResult.Failure(mapException(error)))
            }
        }

    }
}
