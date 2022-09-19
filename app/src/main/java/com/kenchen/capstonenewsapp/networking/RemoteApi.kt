package com.kenchen.capstonenewsapp.networking

import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.model.response.GetNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val BASE_URL = "https://newsapi.org/v2"
const val API_KEY = "7ac9410c8eac436d90d7b8f121b5db36"

class RemoteApi(private val remoteApiService: RemoteApiService) {

    /**
     * get top headlines news by country code
     * */
    fun getTopHeadlinesByCountry(
        countryCode: String,
        OnNewsReceived: (RemoteResult<List<Article>>) -> Unit,
    ) {
        remoteApiService.getTopHeadlinesByCountry(countryCode, API_KEY)
            .enqueue(object : Callback<GetNewsResponse> {
                override fun onResponse(
                    call: Call<GetNewsResponse>,
                    response: Response<GetNewsResponse>,
                ) {
                    val getNewsResponse = response.body()
                    if (getNewsResponse == null) {
                        OnNewsReceived(RemoteResult.Failure(NullPointerException("No response " +
                                "body!")))
                    } else {
                        OnNewsReceived(RemoteResult.Success(getNewsResponse.articles))
                    }
                }

                override fun onFailure(call: Call<GetNewsResponse>, error: Throwable) {
                    OnNewsReceived(RemoteResult.Failure(error))
                }

            })
    }
}
