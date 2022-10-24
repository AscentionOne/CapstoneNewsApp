package com.kenchen.capstonenewsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        private lateinit var instance: App

//        private val apiService by lazy { buildApiService() }
//
//        private val remoteApi by lazy { RemoteApiImp(apiService) }

//        val gson by lazy { Gson() }

//        private val database: NewsDatabase by lazy {
//            NewsDatabase.buildDatabase(instance)
//        }

//        private val prefsStore by lazy {
//            PrefsStoreImpl(instance)
//        }
//
//        private val connectivityManager by lazy {
//            instance.getSystemService(ConnectivityManager::class.java)
//        }
//
//        private val networkStatusChecker by lazy {
//            NetworkStatusChecker(connectivityManager)
//        }

//        val newsRepository: NewsRepository by lazy {
//            NewsRepositoryImp(
//                database.articleDao(),
//                database.sourceDao(),
//                remoteApi,
//                prefsStore,
//                networkStatusChecker,
//            )
//        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
