package com.kenchen.capstonenewsapp.networking

import com.kenchen.capstonenewsapp.model.Article

const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "7ac9410c8eac436d90d7b8f121b5db36"

/**
 * Logic for Api calls
 * */

class RemoteApi(private val remoteApiService: RemoteApiService) {

    /**
     * get top headlines news by country code with Coroutine (suspend)
     * */
    suspend fun getTopHeadlinesByCountry(countryCode: String): List<Article> =
        try {
            val data = remoteApiService.getTopHeadlinesByCountry(countryCode, API_KEY)
            val shuffledNews = data.articles.shuffled()
            shuffledNews
//            RemoteResult.Success(data.articles)
        } catch (error: Exception) {
            throw error
        }

}
