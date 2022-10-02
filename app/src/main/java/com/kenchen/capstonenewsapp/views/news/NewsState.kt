package com.kenchen.capstonenewsapp.views.news

import com.kenchen.capstonenewsapp.model.Article

sealed class NewsState {
    object Loading : NewsState()
    data class Loaded(val articles: List<Article>) : NewsState()
    data class Error(val errorMessage: String) : NewsState()
}
