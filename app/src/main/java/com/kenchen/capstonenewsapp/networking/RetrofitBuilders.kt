package com.kenchen.capstonenewsapp.networking

//const val BASE_URL = "https://newsapi.org/v2/"
//
//fun buildClient(): OkHttpClient =
//    OkHttpClient.Builder()
//        .addInterceptor(HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        })
//        .build()
//
//fun buildRetrofit(): Retrofit {
//    return Retrofit.Builder()
//        .client(buildClient())
//        .baseUrl(BASE_URL)
//        .addConverterFactory(MoshiConverterFactory.create().asLenient())
//        .build()
//}
//
//fun buildApiService(): RemoteApiService {
//    return buildRetrofit().create(RemoteApiService::class.java)
//}
//
//
