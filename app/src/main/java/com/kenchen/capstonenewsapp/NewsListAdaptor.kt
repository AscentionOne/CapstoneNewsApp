package com.kenchen.capstonenewsapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kenchen.capstonenewsapp.views.ArticleView

class NewsListAdaptor(var newsList: List<Article>) : RecyclerView.Adapter<NewsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val articleView = ArticleView(parent.context)
        articleView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        return  NewsListViewHolder(articleView)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bindData(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}
