package com.kenchen.capstonenewsapp

import android.app.Application
import com.kenchen.capstonenewsapp.networking.RemoteApi
import com.kenchen.capstonenewsapp.networking.buildApiService

class App : Application() {

    companion object {
        private lateinit var instance: App

        private val apiService by lazy { buildApiService() }

        val remoteApi by lazy { RemoteApi(apiService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
