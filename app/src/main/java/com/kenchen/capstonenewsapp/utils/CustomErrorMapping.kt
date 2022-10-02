package com.kenchen.capstonenewsapp.utils

import com.kenchen.capstonenewsapp.networking.RemoteError
import okio.IOException
import retrofit2.HttpException


// map to custom remote error
 fun mapException(remoteException: Exception): RemoteError {
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
