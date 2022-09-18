package com.kenchen.capstonenewsapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/*
* Article data class
* */
@Parcelize
data class Article(
    @field:Json(name = "source") val source: Source,
    @field:Json(name = "author") val author: String?,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String? = null,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "urlToImage") val urlToImage: String? = null,
    @field:Json(name = "publishedAt") val publishedAt: String,
    @field:Json(name = "content") val content: String? = null,
) : Parcelable
