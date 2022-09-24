package com.kenchen.capstonenewsapp.views.news

import androidx.recyclerview.widget.RecyclerView
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.views.news.ArticleView

class NewsListViewHolder(private val articleView: ArticleView) : RecyclerView.ViewHolder(articleView) {

    fun bindData(article: Article) {
        articleView.setAuthorText(article.author)
        articleView.setTitleText(article.title)
    }
}
