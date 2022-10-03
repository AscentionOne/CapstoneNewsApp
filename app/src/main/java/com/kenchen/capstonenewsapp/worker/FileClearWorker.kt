package com.kenchen.capstonenewsapp.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Clear the downloaded image file
 */
class FileClearWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

  override fun doWork(): Result {
    val root = applicationContext.externalMediaDirs.first()

    return try {
      root.listFiles()?.forEach { child ->
        if (child.isDirectory) {
          child.deleteRecursively()
        } else {
          child.delete()
        }
      }
      Result.success()
    } catch (error: Throwable) {
      error.printStackTrace()
      Result.failure()
    }
  }
}
