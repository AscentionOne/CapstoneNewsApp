package com.kenchen.capstonenewsapp.networking

import com.kenchen.capstonenewsapp.model.Article
import javax.inject.Inject

const val API_KEY = "7ac9410c8eac436d90d7b8f121b5db36"

/**
 * Logic for Api calls
 * */

class RemoteApiImp @Inject constructor (private val remoteApiService: RemoteApiService): RemoteApi {

    /**
     * get top headlines news by country code with Coroutine (suspend)
     * If there is an error it will be caught in ViewModel
     * */
    override suspend fun getTopHeadlinesByCountry(countryCode: String): List<Article> {
        val data = remoteApiService.getTopHeadlinesByCountry(countryCode, API_KEY)
        return data.articles.shuffled()
    }

}
