package com.kenchen.capstonenewsapp.views.newsdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kenchen.capstonenewsapp.views.MainActivity
import com.kenchen.capstonenewsapp.databinding.ActivityNewsDetailBinding
import com.kenchen.capstonenewsapp.model.Article

class NewsDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val article = intent.getParcelableExtra<Article>(MainActivity.INTENT_ARTICLE_KEY)!!
        title = article.title
        binding.newsDescriptionTextView.text = article.description
    }
}
