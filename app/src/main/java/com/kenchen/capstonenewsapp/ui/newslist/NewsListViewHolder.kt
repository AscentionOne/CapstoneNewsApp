package com.kenchen.capstonenewsapp.ui.newslist

import androidx.recyclerview.widget.RecyclerView
import com.kenchen.capstonenewsapp.model.Article

class NewsListViewHolder(private val articleView: ArticleView) :
    RecyclerView.ViewHolder(articleView) {

    fun bindData(article: Article) {
        articleView.setAuthorText(article.author)
        articleView.setTitleText(article.title)
        if (!article.urlToImage.isNullOrEmpty()) {
            articleView.setNewsImage(article.urlToImage)
        }
    }
}
