package com.kenchen.capstonenewsapp.views.news

import androidx.lifecycle.*
import com.kenchen.capstonenewsapp.App.Companion.remoteApi
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.networking.RemoteError
import com.kenchen.capstonenewsapp.networking.RemoteResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel : ViewModel() {

    private val _headLineNewsLiveData = MutableLiveData<RemoteResult<List<Article>>>()
    val headLineNewsLiveData: LiveData<RemoteResult<List<Article>>> = _headLineNewsLiveData

    fun getTopHeadlinesNewsByCountry() {
        viewModelScope.launch(Dispatchers.IO) {

            val result = remoteApi.getTopHeadlinesByCountry("us")

            // use postValue instead of value because
            // we are on IO dispatcher and not on the
            // Main (UI) thread
            _headLineNewsLiveData.postValue(result)
        }

    }
}
