package com.kenchen.capstonenewsapp.model

import android.os.Parcelable
import com.kenchen.capstonenewsapp.model.Category
import com.kenchen.capstonenewsapp.model.Country
import com.kenchen.capstonenewsapp.model.Language
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/*
* Source data class
* */
@Parcelize
data class Source(
    @field:Json(name = "id") val id: String? = null,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "description") val description: String? = null,
    @field:Json(name = "url") val url: String? = null,
    @field:Json(name = "category") val category: String? = null,
    @field:Json(name = "language") val language: String? = null,
    @field:Json(name = "country") val country: String? = null,
) : Parcelable
