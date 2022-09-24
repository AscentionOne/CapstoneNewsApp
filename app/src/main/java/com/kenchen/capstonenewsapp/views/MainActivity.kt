package com.kenchen.capstonenewsapp.views

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kenchen.capstonenewsapp.App
import com.kenchen.capstonenewsapp.InMemoryNewsServiceImp
import com.kenchen.capstonenewsapp.R
import com.kenchen.capstonenewsapp.databinding.ActivityMainBinding
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.networking.NetworkStatusChecker
import com.kenchen.capstonenewsapp.networking.RemoteError
import com.kenchen.capstonenewsapp.networking.RemoteResult
import com.kenchen.capstonenewsapp.utils.toast
import com.kenchen.capstonenewsapp.views.news.NewsListAdaptor
import com.kenchen.capstonenewsapp.views.newsdetails.NewsDetailActivity

class MainActivity : AppCompatActivity() {
    // using view binding
    private lateinit var binding: ActivityMainBinding
    private val remoteApi = App.remoteApi

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(this.getSystemService(ConnectivityManager::class.java))
    }

    companion object {
        const val INTENT_ARTICLE_KEY = "newsParcel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mockNewsService = InMemoryNewsServiceImp(this)
        val dummyNews = mockNewsService.getDummyNews().filterNotNull()

//        mockNewsService.saveNews()

        getTopHeadlinesNewsByCountry()

        setUpSwipeToRefresh()
 
    }

    private fun setUpSwipeToRefresh() {
        binding.swipeRefreshLayout.run {
            // set loading indicator color
            setColorSchemeColors(
                getColor(R.color.purple_200),
                getColor(R.color.teal_200),
            )

            setOnRefreshListener {
                getTopHeadlinesNewsByCountry()
                isRefreshing = false // remove the loading indicator
            }
        }
    }

    private fun showNewsDetail(article: Article) {
        val newsDetail = Intent(this, NewsDetailActivity::class.java)
        newsDetail.putExtra(INTENT_ARTICLE_KEY, article)
        startActivity(newsDetail)
    }

    private fun getTopHeadlinesNewsByCountry() {
        networkStatusChecker.performIfConnectedToInternet {
            remoteApi.getTopHeadlinesByCountry("us") { result ->
                when (result) {
                    is RemoteResult.Success -> {
                        val data = result.value.shuffled()
                        onFetchNewsSuccess(data)
//                        onFetchNewsSuccess(result.value)
                    }
                    is RemoteResult.Failure -> {
                        // return different error message based on the error
                        when (result.error) {
                            is RemoteError.UnAuthorized -> {
                                onFetchNewsError(result.error.message)
                            }
                            else -> {
                                onFetchNewsError(result.error.message.toString())
                            }
                        }

                    }
                }
            }
        }
    }

    private fun onFetchNewsSuccess(articles: List<Article>) {
        showNews(articles)
    }

    private fun showNews(articles: List<Article>) {
        binding.newsListRecyclerview.run {
            adapter = NewsListAdaptor(articles) { article ->
                showNewsDetail(article)
            }
        }
    }

    private fun onFetchNewsError(errorMsg: String) {
        this.toast(errorMsg)
    }

}
