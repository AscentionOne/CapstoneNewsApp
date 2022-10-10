package com.kenchen.capstonenewsapp

import android.app.Application
import com.google.gson.Gson
import com.kenchen.capstonenewsapp.database.NewsDatabase
import com.kenchen.capstonenewsapp.networking.RemoteApi
import com.kenchen.capstonenewsapp.networking.buildApiService
import com.kenchen.capstonenewsapp.prefsstore.PrefsStoreImpl
import com.kenchen.capstonenewsapp.repository.NewsRepository
import com.kenchen.capstonenewsapp.repository.NewsRepositoryImp

class App : Application() {

    companion object {
        private lateinit var instance: App

        private val apiService by lazy { buildApiService() }

        val remoteApi by lazy { RemoteApi(apiService) }

        val gson by lazy { Gson() }

        private val database: NewsDatabase by lazy {
            NewsDatabase.buildDatabase(instance)
        }

        private val prefsStore by lazy {
            PrefsStoreImpl(instance)
        }

        val newsRepository: NewsRepository by lazy {
            NewsRepositoryImp(
                database.articleDao(),
                database.sourceDao(),
                remoteApi,
                prefsStore,
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
