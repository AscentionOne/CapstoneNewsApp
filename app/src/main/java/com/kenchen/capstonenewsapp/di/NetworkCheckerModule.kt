package com.kenchen.capstonenewsapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.kenchen.capstonenewsapp.networking.NetworkStatusChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object NetworkCheckerModule {

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java)
    }

    @Provides
    fun provideNetworkStatusChecker(connectivityManager: ConnectivityManager): NetworkStatusChecker {
        return NetworkStatusChecker(connectivityManager)
    }
}
