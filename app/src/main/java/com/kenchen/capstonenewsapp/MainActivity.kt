package com.kenchen.capstonenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.kenchen.capstonenewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // using view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        updateNewsTextView(view)
    }

    // update the news textView text
    private fun updateNewsTextView(view: ConstraintLayout) {
        val mockNewsService = NewsService()
        val dummyNews = mockNewsService.getDummyNews()
        val mainGroup = view.children
        var newsCount = 1 // index in newList map start at 1
        for (i in mainGroup) {
            if (i is TextView) {
                val news = dummyNews[newsCount]
                if (news != null) {
                    i.text = getString(R.string.article_title, news.title,
                        news.author, news.source.name)
                }
                newsCount++
            }
        }
    }


}
