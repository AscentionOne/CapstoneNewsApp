package com.kenchen.capstonenewsapp

/*
* Article data class
* */
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null,
)
