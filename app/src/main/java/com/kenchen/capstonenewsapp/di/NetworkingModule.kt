package com.kenchen.capstonenewsapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.kenchen.capstonenewsapp.networking.NetworkStatusChecker
import com.kenchen.capstonenewsapp.networking.RemoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


const val BASE_URL = "https://newsapi.org/v2/"

@InstallIn(SingletonComponent::class)
@Module
object NetworkingModule {

    // Retrofit + Remote api service
    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create().asLenient()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun buildRetrofit(client: OkHttpClient ,converterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun buildApiService(retrofit: Retrofit): RemoteApiService =
        retrofit.create(RemoteApiService::class.java)

    // Network connectivity
    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkStatusChecker(connectivityManager: ConnectivityManager): NetworkStatusChecker {
        return NetworkStatusChecker(connectivityManager)
    }
}
