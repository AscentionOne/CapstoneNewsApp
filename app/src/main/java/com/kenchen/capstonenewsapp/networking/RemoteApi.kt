package com.kenchen.capstonenewsapp.networking

import android.util.Log
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.model.response.GetNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.Exception

const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "7ac9410c8eac436d90d7b8f121b5db36"

/**
 * Logic for Api calls
 * */

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
                    //TODO: return the status code, check other error code
//                    try {
//
//                    val getNewsResponse = response.body()
//                        if (getNewsResponse == null) {
//                            Log.d("Debug", "Got response but null")
//                            Log.d("Debug", response.code().toString())
//                            Log.d("Debug", response.message())
//                            Log.d("Debug", response.errorBody().toString())
////                        OnNewsReceived(RemoteResult.Failure(NullPointerException("No response " +
////                                "body!")))
//                            OnNewsReceived(RemoteResult.Failure(RemoteError.UnAuthorized
//                                ("Unauthenticated")))
//                        } else {
//                            OnNewsReceived(RemoteResult.Success(getNewsResponse.articles))
//                        }
//                    } catch(throwable: Throwable) {
//                        android.util.Log.d("Debug", throwable.toString())
//                    }

                    if (response.isSuccessful) {
                        Log.d("Debug", "Success")
                        val getNewsResponse = response.body()
                        OnNewsReceived(RemoteResult.Success(getNewsResponse?.articles ?:
                        emptyList<Article>()))
                    } else {
                        Log.d("Debug", "Failed")
                        Log.d("Debug", response.code().toString())
                        OnNewsReceived(RemoteResult.Failure(RemoteError.UnAuthorized
                            ("Unauthenticated")))
                    }

//                    try {
//
//                    } catch (error : Throwable) {
//                        if (error is HttpException) {
//                            Log.d("Debug", "Http exception)
//                        }
//                    }

                }

                override fun onFailure(call: Call<GetNewsResponse>, error: Throwable) {
                    Log.d("Debug", error.toString())
                    OnNewsReceived(RemoteResult.Failure(RemoteError.InternalServerError("Server " +
                            "Error")))
                }
            })
    }
}


class CustomException(private val msg:String) : Exception(msg)
