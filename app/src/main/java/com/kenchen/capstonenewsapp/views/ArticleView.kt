package com.kenchen.capstonenewsapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.kenchen.capstonenewsapp.R
import com.kenchen.capstonenewsapp.databinding.ArticleViewBinding

class ArticleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = ArticleViewBinding.inflate(LayoutInflater.from(context), this)

    fun setTitleText(name: String) {
        binding.titleTextView.text = resources.getString(R.string.news_title, name)
    }

    fun setAuthorText(name: String?) {
        binding.authorTextView.text = resources.getString(R.string.news_author, name ?: "Unknown")
    }
}
