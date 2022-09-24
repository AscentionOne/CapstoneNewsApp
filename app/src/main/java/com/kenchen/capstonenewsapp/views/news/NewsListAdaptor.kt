package com.kenchen.capstonenewsapp.views.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kenchen.capstonenewsapp.model.Article

class NewsListAdaptor(
    private var articles: List<Article>,
    val onArticleClicked: (Article) -> Unit,
) : RecyclerView.Adapter<NewsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val articleView = ArticleView(parent.context)
        articleView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        return NewsListViewHolder(articleView)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bindData(articles[position])
        holder.itemView.setOnClickListener {
            onArticleClicked(articles[position])
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
