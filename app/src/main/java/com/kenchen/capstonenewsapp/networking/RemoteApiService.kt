package com.kenchen.capstonenewsapp.networking

import com.kenchen.capstonenewsapp.model.response.GetNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {

    @GET("top-headlines")
    fun getTopHeadlinesByCountry(@Query("country") country: String, @Query("apiKey") apiKey: String):
            Call<GetNewsResponse>
}
