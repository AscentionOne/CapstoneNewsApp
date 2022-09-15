package com.kenchen.capstonenewsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kenchen.capstonenewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    // using view binding
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val INTENT_NEWS_DESCRIPTION_KEY = "newsDescription"
        const val INTENT_NEWS_TITLE_KEY = "newsTitle"
        const val INTENT_NEWS_KEY = "newsParcel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mockNewsService = InMemoryNewsServiceImp(this)
        val dummyNews = mockNewsService.getDummyNews().filterNotNull()

        mockNewsService.saveNews()

        binding.newsListRecyclerview.run {
            adapter = NewsListAdaptor(dummyNews) { article ->
                showNewsDetail(article)
            }
        }
    }

    private fun showNewsDetail(article: Article) {
        val newsDetail = Intent(this, NewsDetailActivity::class.java)
        newsDetail.putExtra(INTENT_NEWS_DESCRIPTION_KEY, article.description)
        newsDetail.putExtra(INTENT_NEWS_TITLE_KEY, article.title)
        newsDetail.putExtra(INTENT_NEWS_KEY, article)
        startActivity(newsDetail)
    }

//    override fun newsListClicked(article: Article) {
//        showNewsDetail(article)
//    }

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
