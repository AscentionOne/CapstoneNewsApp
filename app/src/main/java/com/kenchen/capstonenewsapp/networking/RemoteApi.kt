package com.kenchen.capstonenewsapp.networking

import com.kenchen.capstonenewsapp.model.Article

interface RemoteApi {
    suspend fun getTopHeadlinesByCountry(countryCode: String): List<Article>
}
