package com.kenchen.capstonenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kenchen.capstonenewsapp.databinding.ActivityMainBinding
import com.kenchen.capstonenewsapp.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = intent.getStringExtra(MainActivity.INTENT_NEWS_TITLE_KEY)
        val description = intent.getStringExtra(MainActivity.INTENT_NEWS_DESCRIPTION_KEY)
        binding.newsDescriptionTextView.text = description
    }
}
