package com.kenchen.capstonenewsapp.model

import com.kenchen.capstonenewsapp.networking.RemoteError

/**
 * A State wrapper that is passed back to UI.
 * This is good for separating the data from repository and UI layer
 * UI only needs to know the sate of the data and render accordingly
 * can have: loading, ready(loaded), partial(if we have local database), error state
 * */

sealed class ArticleState {
    data class Ready(val articles: List<Article>) : ArticleState()
    data class Partial(val articles: List<Article>, val error: RemoteError) : ArticleState()
}
