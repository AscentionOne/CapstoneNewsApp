package com.kenchen.capstonenewsapp.ui.newslist

import com.kenchen.capstonenewsapp.model.Article

sealed class NewsState {
    object Loading : NewsState()
    data class Loaded(val articles: List<Article>) : NewsState()
    data class Error(val errorMessage: String) : NewsState()
}
