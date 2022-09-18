package com.kenchen.capstonenewsapp.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit

fun buildClient(): OkHttpClient =
    OkHttpClient.Builder()
        .build()

fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .build()
}

fun buildApiService(): RemoteApiService {
    return buildRetrofit().create(RemoteApiService::class.java)
}


