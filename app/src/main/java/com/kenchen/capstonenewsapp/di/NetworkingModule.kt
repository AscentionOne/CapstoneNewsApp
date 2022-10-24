package com.kenchen.capstonenewsapp.di

import com.kenchen.capstonenewsapp.networking.BASE_URL
import com.kenchen.capstonenewsapp.networking.RemoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


const val BASE_URL = "https://newsapi.org/v2/"

@InstallIn(SingletonComponent::class)
@Module
object NetworkingModule {

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create().asLenient()
    }

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun buildRetrofit(client: OkHttpClient ,converterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun buildApiService(retrofit: Retrofit): RemoteApiService =
        retrofit.create(RemoteApiService::class.java)
}
