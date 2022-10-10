package com.kenchen.capstonenewsapp.views

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.kenchen.capstonenewsapp.App
import com.kenchen.capstonenewsapp.R
import com.kenchen.capstonenewsapp.databinding.ActivityMainBinding
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.networking.NetworkStatusChecker
import com.kenchen.capstonenewsapp.networking.RemoteError
import com.kenchen.capstonenewsapp.networking.RemoteResult
import com.kenchen.capstonenewsapp.utils.gone
import com.kenchen.capstonenewsapp.utils.toast
import com.kenchen.capstonenewsapp.utils.visible
import com.kenchen.capstonenewsapp.views.news.NewsListAdaptor
import com.kenchen.capstonenewsapp.views.news.NewsLoadingState
import com.kenchen.capstonenewsapp.views.news.NewsViewModel
import com.kenchen.capstonenewsapp.views.newsdetails.NewsDetailActivity

class MainActivity : AppCompatActivity() {
    // using view binding
    private lateinit var binding: ActivityMainBinding
    private val remoteApi = App.remoteApi

    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModel.Factory(App.newsRepository)
    }

    private var isDataUsage = false

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

        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // search is done on text changes
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text?.let { searchQuery ->
                    newsViewModel.searchArticles(searchQuery)
                }
                return true
            }
        }

        binding.searchView.setOnQueryTextListener(queryTextListener)

//        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        initialiseObservers()
//        setUpSwipeToRefresh()
//        newsViewModel.onActivityReady()
    }

    // set up swipe to refresh
    private fun setUpSwipeToRefresh() {
//        binding.swipeRefreshLayout.run {
//            // set loading indicator color
//            setColorSchemeColors(
//                getColor(R.color.purple_200),
//                getColor(R.color.teal_200),
//            )
//
//            setOnRefreshListener {
//                newsViewModel.refreshNews()
//                Log.d("Debug", "Refresh")
//                isRefreshing = false // remove the loading indicator
//            }
//        }
    }

    // navigate to news detail activity
    private fun showNewsDetail(article: Article) {
        val newsDetail = Intent(this, NewsDetailActivity::class.java)
        newsDetail.putExtra(INTENT_ARTICLE_KEY, article)
        startActivity(newsDetail)
    }

    private fun initialiseObservers() {
        Log.d("Debug", "123")
        newsViewModel.headLineNewsLiveData.observe(this) { result ->
            when (result) {
                is RemoteResult.Success -> {
                    // TODO: show loading indicator
                    showNews(result.value)
                }
                is RemoteResult.Failure -> showError(
                    when (result.error) {
                        is RemoteError.ApiException -> result.error.message
                        else -> result.error.message
                    }
                )
            }
        }

//        newsViewModel.newsLoadingStateLiveData.observe(this) { state ->
//            onNewsLoadingStateChanged(state)
//        }
    }

    // show news in recycler view
    private fun showNews(articles: List<Article>) {
        binding.newsListRecyclerview.run {
            adapter = NewsListAdaptor(articles) { article ->
                showNewsDetail(article)
            }
        }
    }

    // show error toast message
    private fun showError(errorMsg: String) {
        this.toast(errorMsg)
    }

    // show news loading state
    private fun onNewsLoadingStateChanged(state: NewsLoadingState) {
        when (state) {
            NewsLoadingState.LOADING -> {
                binding.newsLoadingErrorTextView.gone()
                binding.newsListRecyclerview.gone()
                binding.loadingProgressBar.visible()
            }
            NewsLoadingState.LOADED -> {
                binding.loadingProgressBar.gone()
                binding.newsListRecyclerview.visible()
            }
            NewsLoadingState.ERROR -> {
                binding.loadingProgressBar.gone()
                binding.newsLoadingErrorTextView.visible()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.has_wifi) {
            newsViewModel.toggleDataUsage()
        }
        return true
    }

//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        if (isDataUsage) {
//            menu?.findItem(R.id.has_wifi)?.setIcon(R.drawable.ic_baseline_wifi_24)
//        } else {
//            menu?.findItem(R.id.has_wifi)?.setIcon(R.drawable.ic_baseline_wifi_off_24)
//        }
//        return true
//    }
}
