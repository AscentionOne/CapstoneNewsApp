package com.kenchen.capstonenewsapp.views.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenchen.capstonenewsapp.App.Companion.remoteApi
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.networking.RemoteResult
import com.kenchen.capstonenewsapp.utils.mapException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _headLineNewsLiveData = MutableLiveData<RemoteResult<List<Article>>>()
    val headLineNewsLiveData: LiveData<RemoteResult<List<Article>>> = _headLineNewsLiveData

    private val test = MutableLiveData<List<String>>()

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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = remoteApi.getTopHeadlinesByCountry("us")

                // use postValue instead of value because
                // we are on IO dispatcher and not on the
                // Main (UI) thread
                _headLineNewsLiveData.postValue(RemoteResult.Success(result))
                _newsLoadingStateLiveData.postValue(NewsLoadingState.LOADED)
            } catch (error: Exception) {
                _newsLoadingStateLiveData.postValue(NewsLoadingState.ERROR)
                _headLineNewsLiveData.postValue(RemoteResult.Failure(mapException(error)))
            }
        }
    }
}
