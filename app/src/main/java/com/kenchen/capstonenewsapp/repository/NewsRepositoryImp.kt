package com.kenchen.capstonenewsapp.repository

import android.util.Log
import com.kenchen.capstonenewsapp.database.dao.ArticleDao
import com.kenchen.capstonenewsapp.database.dao.SourceDao
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.model.Source
import com.kenchen.capstonenewsapp.networking.RemoteApi
import com.kenchen.capstonenewsapp.networking.RemoteResult
import com.kenchen.capstonenewsapp.prefsstore.PrefsStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImp(
    private val articleDao: ArticleDao,
    private val sourceDao: SourceDao,
    private val remoteApi: RemoteApi,
    private val prefsStore: PrefsStore,
) : NewsRepository {

    companion object {
        private const val TAG = "NewsRepositoryImpl"
    }

    override fun getArticles(): Flow<RemoteResult<List<Article>>> {
        return flow {

            // get from local Room database first
            val newsArticles = articleDao.getArticles()

            emit(RemoteResult.Success(newsArticles))

            Log.i(TAG, "Articles from local database size = ${newsArticles.size}")

            // Not sure how to use the prefsStore flow value here

            try {
                val result = remoteApi.getTopHeadlinesByCountry("us")

                emit(RemoteResult.Success(result))

                // if network data is not empty, add to database
                if (result.isNotEmpty()) {
                    articleDao.addArticles(result)
                }

            } catch (error: Exception) {
//                emit(RemoteResult.Failure(mapException(error)))
                // log the error for team to inspect
                Log.i(TAG, "Articles from local database network error")
            }

        }
    }

    override suspend fun searchArticles(search: String): List<Article> = articleDao
        .searchArticles(search)

    override suspend fun addArticles(articles: List<Article>) = articleDao.addArticles(articles)

    override suspend fun clearArticles() = articleDao.clearArticles()

    override suspend fun getSources(): List<Source> = sourceDao.getSources()

    override suspend fun addSources(sources: List<Source>) = sourceDao.addSources(sources)

    override suspend fun toggleDataUsage() = prefsStore.toggleDataUsage()

}
