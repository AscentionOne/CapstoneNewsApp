package com.kenchen.capstonenewsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
* Article data class
* */
@Parcelize
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String? = null,
) : Parcelable
