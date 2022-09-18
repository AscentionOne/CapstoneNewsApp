package com.kenchen.capstonenewsapp.networking

import java.lang.Exception

/**
 * Network response handling
 * */

sealed class RemoteResult<out T> {
    data class Success<T>(val value: T) : RemoteResult<T>()
    data class Failure(val exception: Exception) : RemoteResult<Nothing>()
}
