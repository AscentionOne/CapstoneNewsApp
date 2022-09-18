package com.kenchen.capstonenewsapp

import android.os.Parcelable
import com.kenchen.capstonenewsapp.model.Category
import com.kenchen.capstonenewsapp.model.Country
import com.kenchen.capstonenewsapp.model.Language
import kotlinx.parcelize.Parcelize

/*
* Source data class
* */
@Parcelize
data class Source(
    val id: String? = null,
    val name: String,
    val description: String? = null,
    val url: String? = null,
    val category: Category? = null,
    val language: Language? = null,
    val country: Country? = null,
) : Parcelable
