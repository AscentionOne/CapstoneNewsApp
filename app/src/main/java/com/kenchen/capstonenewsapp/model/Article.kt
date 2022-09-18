package com.kenchen.capstonenewsapp.model

import android.os.Parcelable
import com.kenchen.capstonenewsapp.Source
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
