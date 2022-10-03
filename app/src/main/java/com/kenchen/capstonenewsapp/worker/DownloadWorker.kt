package com.kenchen.capstonenewsapp.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Download image worker
 */

class DownloadWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        val imageUrlString = inputData.getString("image_url") ?: return Result.failure()
        val imageUrl = URL(imageUrlString)
        val connection = imageUrl.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()

        // there is no good way of naming the file. Splitting from imageUrl
        // to get the name is unstable. Here I use current system time for
        // the file name (might be able to improve this)
        val imageName = "${System.currentTimeMillis()}.jpg"
        val file = File(applicationContext.externalMediaDirs.first(), imageName)

        Log.d("Debug", applicationContext.externalMediaDirs.first().toString())

        val inputStream = connection.inputStream

        val outputStream = FileOutputStream(file)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024)

            var byteCount = inputStream.read(buffer)

            while (byteCount > 0) {
                output.write(buffer, 0, byteCount)

                byteCount = inputStream.read(buffer)
            }

            output.flush()
        }
        val output = workDataOf("image_path" to file.absolutePath)
        return Result.success(output)
    }
}
