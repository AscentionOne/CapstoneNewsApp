package com.kenchen.capstonenewsapp.prefsstore

import kotlinx.coroutines.flow.Flow

interface PrefsStore {
    fun isDataUsage(): Flow<Boolean>

    suspend fun toggleDataUsage()
}
