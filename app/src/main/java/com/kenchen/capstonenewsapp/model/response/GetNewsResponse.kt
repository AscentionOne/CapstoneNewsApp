package com.kenchen.capstonenewsapp.model.response

import com.kenchen.capstonenewsapp.model.Article
import com.squareup.moshi.Json

data class GetNewsResponse(
    @field:Json(name = "articles") val articles: List<Article> = mutableListOf(),
)
