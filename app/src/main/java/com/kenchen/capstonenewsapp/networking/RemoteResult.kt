package com.kenchen.capstonenewsapp.networking

/**
 * Network response handling
 * */

sealed class RemoteResult<out T> {
    data class Success<T>(val value: T) : RemoteResult<T>()
    data class Failure(val error: RemoteError) : RemoteResult<Nothing>()
//    data class Failure(val error: Throwable?) : RemoteResult<Nothing>()
}
