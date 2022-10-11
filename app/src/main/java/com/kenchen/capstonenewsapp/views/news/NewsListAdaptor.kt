package com.kenchen.capstonenewsapp.views.news

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kenchen.capstonenewsapp.model.Article

class NewsListAdaptor(
//    articles: List<Article>,
    val onArticleClicked: (Article) -> Unit,
) : RecyclerView.Adapter<NewsListViewHolder>() {

    private val internalArticles = mutableListOf<Article>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(articles: List<Article>) {
        internalArticles.clear()
        internalArticles.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val articleView = ArticleView(parent.context)
        articleView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        return NewsListViewHolder(articleView)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bindData(internalArticles[position])
        holder.itemView.setOnClickListener {
            onArticleClicked(internalArticles[position])
        }
    }

    override fun getItemCount(): Int {
        return internalArticles.size
    }
}

// DTO  ->
