package com.kenchen.capstonenewsapp.repository

import android.util.Log
import com.kenchen.capstonenewsapp.database.dao.ArticleDao
import com.kenchen.capstonenewsapp.database.dao.SourceDao
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.model.ArticleState
import com.kenchen.capstonenewsapp.model.Source
import com.kenchen.capstonenewsapp.networking.NetworkStatusChecker
import com.kenchen.capstonenewsapp.networking.RemoteApi
import com.kenchen.capstonenewsapp.prefsstore.PrefsStore
import com.kenchen.capstonenewsapp.utils.mapException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class NewsRepositoryImp(
    private val articleDao: ArticleDao,
    private val sourceDao: SourceDao,
    private val remoteApi: RemoteApi,
    private val prefsStore: PrefsStore,
    private val networkStatusChecker: NetworkStatusChecker,
) : NewsRepository {

    companion object {
        private const val TAG = "NewsRepositoryImpl"
    }

    override fun getArticles(): Flow<ArticleState> {
        return flow<ArticleState> {
            // can emit ArticleState.loading() here for loading state
            // always get from local Room database first
            val newsArticles = articleDao.getArticles()

            emit(ArticleState.Ready(newsArticles))

            Log.i(TAG, "Articles from local database size = ${newsArticles.size}")

            val fetchDataOnWifiOnly = prefsStore.isDataUsage().first()

            val isOnWifi = networkStatusChecker.isOnWifiConnection()

            println("here")
            println(fetchDataOnWifiOnly)
            println(isOnWifi)
            // only fetch the data when
            // 1. there is wifi connection
            // 2. unclick the has wifi on menu item, means user can use data without wifi
            if (fetchDataOnWifiOnly.not() || isOnWifi) {
                Log.i(TAG, "Fetching from network")
                try {
                    println("success")
                    val result = remoteApi.getTopHeadlinesByCountry("us")
                    println("success1")

                    emit(ArticleState.Ready(result))
                    println("success2")

                    // if network data is not empty, means the source of truth
                    // add to database
                    if (result.isNotEmpty()) {
                        println("success3")

                        // clear first to prevent stacking up old articles
                        articleDao.clearArticles()
                        articleDao.addArticles(result)
                    }
                    println("success4")


                } catch (error: Exception) {
                    println("Throw error")

                    // emit partial to show that data is coming from Room database not from
                    // remote API
                    emit(ArticleState.Partial(newsArticles, mapException(error)))
                    // log the error for team to inspect
                    Log.e(TAG, "Articles from local database network error")
                }
            } else {
                println("Not fetching from network")
                Log.i(TAG, "Not fetching from network")
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

    override fun isDataUsage(): Flow<Boolean> = prefsStore.isDataUsage()
}
