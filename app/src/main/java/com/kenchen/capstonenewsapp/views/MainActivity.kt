package com.kenchen.capstonenewsapp.views

import android.content.Intent
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
import com.kenchen.capstonenewsapp.model.ArticleState
import com.kenchen.capstonenewsapp.networking.RemoteError
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

    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModel.Factory(App.newsRepository)
    }

    private var isDataUsage = false

    private val adapter: NewsListAdaptor = NewsListAdaptor{ article ->
        showNewsDetail(article)
    }

    companion object {
        const val INTENT_ARTICLE_KEY = "newsParcel"
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // setting up adapter
        binding.newsListRecyclerview.adapter = adapter

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

        initialiseObservers()
        setUpSwipeToRefresh()
//        newsViewModel.onActivityReady()
    }

    // set up swipe to refresh
    private fun setUpSwipeToRefresh() {
        binding.swipeRefreshLayout.run {
            // set loading indicator color
            setColorSchemeColors(
                getColor(R.color.purple_200),
                getColor(R.color.teal_200),
            )

            setOnRefreshListener {
//                newsViewModel.refreshNews()
                newsViewModel.fetchArticle()
                Log.d("Debug", "Refresh")
                isRefreshing = false // remove the loading indicator
            }
        }
    }

    // navigate to news detail activity
    private fun showNewsDetail(article: Article) {
        val newsDetail = Intent(this, NewsDetailActivity::class.java)
        newsDetail.putExtra(INTENT_ARTICLE_KEY, article)
        startActivity(newsDetail)
    }

    // observing the life data from newsViewModel
    private fun initialiseObservers() {
        Log.i(TAG, "Initializing observers ...")
        newsViewModel.headLineNewsLiveData.observe(this) { result ->
            when (result) {
                // TODO: should change to use NewsState sealed class
                is ArticleState.Ready -> {
                    // TODO: show loading indicator
                    showNews(result.articles)
                }
                is ArticleState.Partial -> {
                    showNews(result.articles)
                    showError(
                        when (result.error) {
                            is RemoteError.ApiException -> result.error.message
                            else -> result.error.message
                        }
                    )
                }
            }
        }

        newsViewModel.isDataUsageLiveData.observe(this) {
            isDataUsage = it
            updateMenuItem()
        }

//        newsViewModel.newsLoadingStateLiveData.observe(this) { state ->
//            onNewsLoadingStateChanged(state)
//        }
    }

    // show news in recycler view
    private fun showNews(articles: List<Article>) {
        // use updateData function to update data
        // benefit is not instantiating the adaptor instance every time
        // showNews is ran
        adapter.updateData(articles)
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
            NewsLoadingState.LOADED -> { // Ready
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

    private var wifiMenuItem: MenuItem? = null

    // update the menu item based on the isDataUsageLiveData from newsViewModel
    private fun updateMenuItem() {
        if (isDataUsage) {
            wifiMenuItem?.setIcon(R.drawable.ic_baseline_wifi_24)
        } else {
            wifiMenuItem?.setIcon(R.drawable.ic_baseline_wifi_off_24)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        // getting the reference of menuItem
        wifiMenuItem = menu?.findItem(R.id.has_wifi)
        return true
    }
}
