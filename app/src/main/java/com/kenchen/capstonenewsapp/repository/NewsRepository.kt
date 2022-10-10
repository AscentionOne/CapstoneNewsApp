package com.kenchen.capstonenewsapp.repository

import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.model.Source
import com.kenchen.capstonenewsapp.networking.RemoteResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getArticles(): Flow<RemoteResult<List<Article>>>

    suspend fun addArticles(articles: List<Article>)

    suspend fun clearArticles()

    suspend fun searchArticles(search: String): List<Article>

    suspend fun getSources(): List<Source>

    suspend fun addSources(sources: List<Source>)

    suspend fun toggleDataUsage()
}
