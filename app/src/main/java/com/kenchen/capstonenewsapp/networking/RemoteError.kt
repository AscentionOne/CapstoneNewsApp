package com.kenchen.capstonenewsapp.networking

sealed class RemoteError(override val message: String) : Exception() {
    //    data class NetworkConnection(override val message: String) : RemoteError(message)
//    // 400
//    data class BadRequest(override val message: String) : RemoteError(message)
//    // 401
//    data class UnAuthorized(override val message: String) : RemoteError(message)
//    // 404
//    data class ResourceNotFound(override val message: String) : RemoteError(message)
//    // > 500
//    data class InternalServerError(override val message: String) : RemoteError(message)
    data class ApiException(val statusCode: String) : RemoteError(statusCode)
    data class NoInternetException(override val message: String) : RemoteError(message)
    data class UnexpectedException(override val message: String) : RemoteError(message)
}
