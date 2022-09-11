package com.kenchen.capstonenewsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.kenchen.capstonenewsapp.databinding.ActivityMainBinding
import com.kenchen.capstonenewsapp.views.ArticleView

class MainActivity : AppCompatActivity() {
    // using view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mockNewsService = InMemoryNewsServiceImpl()
        val dummyNews = mockNewsService.getDummyNews().filterNotNull()

        binding.newsListRecyclerview.run {
            adapter = NewsListAdaptor(dummyNews)
        }

//        updateArticleView()
    }

    // show article view
//    private fun updateArticleView() {
//        val mockNewsService = InMemoryNewsServiceImpl()
//        val dummyNews = mockNewsService.getDummyNews().filterNotNull()

//        dummyNews.forEach { newsArticle ->
////            ArticleViewBinding.inflate(layoutInflater, binding.newsContainer, true).apply {
////                titleTextView.text = getString(R.string.news_title, newsArticle.value.title)
////                authorTextView.text = getString(R.string.news_author, newsArticle.value.author)
////            }
//            val articleView = ArticleView(this)
//            articleView.setTitleText(newsArticle.title)
//            articleView.setAuthorText(newsArticle.author)
//            binding.newsContainer.addView(articleView)
//        }
//    }
}
