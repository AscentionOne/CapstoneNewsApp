package com.kenchen.capstonenewsapp

import androidx.recyclerview.widget.RecyclerView
import com.kenchen.capstonenewsapp.views.ArticleView

class NewsListViewHolder(private val articleView: ArticleView) : RecyclerView.ViewHolder(articleView) {

    fun bindData(article: Article) {
        articleView.setAuthorText(article.author)
        articleView.setTitleText(article.title)
    }
}
