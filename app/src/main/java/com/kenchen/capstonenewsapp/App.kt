package com.kenchen.capstonenewsapp

import android.app.Application
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.kenchen.capstonenewsapp.database.NewsDatabase
import com.kenchen.capstonenewsapp.networking.NetworkStatusChecker
import com.kenchen.capstonenewsapp.networking.RemoteApi
import com.kenchen.capstonenewsapp.networking.buildApiService
import com.kenchen.capstonenewsapp.prefsstore.PrefsStoreImpl
import com.kenchen.capstonenewsapp.repository.NewsRepository
import com.kenchen.capstonenewsapp.repository.NewsRepositoryImp

class App : Application() {

    companion object {
        private lateinit var instance: App

        private val apiService by lazy { buildApiService() }

        private val remoteApi by lazy { RemoteApi(apiService) }

        val gson by lazy { Gson() }

        private val database: NewsDatabase by lazy {
            NewsDatabase.buildDatabase(instance)
        }

        val prefsStore by lazy {
            PrefsStoreImpl(instance)
        }

        private val connectivityManager by lazy {
            instance.getSystemService(ConnectivityManager::class.java)
        }

        private val networkStatusChecker by lazy {
            NetworkStatusChecker(connectivityManager)
        }

        val newsRepository: NewsRepository by lazy {
            NewsRepositoryImp(
                database.articleDao(),
                database.sourceDao(),
                remoteApi,
                prefsStore,
                networkStatusChecker,
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
