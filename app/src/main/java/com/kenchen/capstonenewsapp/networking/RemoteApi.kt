package com.kenchen.capstonenewsapp.networking

import android.util.Log
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.model.response.GetNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
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
     * get top headlines news by country code with Coroutine (suspend)
     * */
    suspend fun getTopHeadlinesByCountry(countryCode: String): RemoteResult<List<Article>> =
        try {
            val data = remoteApiService.getTopHeadlinesByCountry(countryCode, API_KEY)
            val shuffledNews = data.articles.shuffled()
            RemoteResult.Success(shuffledNews)
//            RemoteResult.Success(data.articles)
        } catch (error: Exception) {

            Log.d("Debug", error.toString())
            Log.d("Debug", error.message.toString())
            RemoteResult.Failure(mapException(error))
        }

    // map to custom remote error
    private fun mapException(remoteException: Exception): RemoteError {
        return when (remoteException) {
            is IOException -> RemoteError.NoInternetException("Please check your connection")
            is HttpException -> RemoteError.ApiException(mapHttpExceptionMessage(remoteException
                .code().toInt()))
            else -> RemoteError.UnexpectedException("Unexpected Error")
        }
    }

    // map custom http exception message based on status code
    private fun mapHttpExceptionMessage(statusCode: Int): String {
        return if (statusCode == 400) {
            "Bad Request"
        } else if (statusCode == 401) {
            "UnAuthorized"
        } else if (statusCode == 404) {
            "Resource not found"
        } else if (statusCode >= 500) {
            "Internal server error"
        } else {
            "Unknown error"
        }
    }

}
