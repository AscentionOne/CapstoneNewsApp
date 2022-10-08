package com.kenchen.capstonenewsapp.worker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.raywenderlich.android.introapp.ImageUtils
import java.io.FileOutputStream

/**
 * Sepia image filer worker
 */

class SepiaFilterWorker(context: Context, workerParameters: WorkerParameters) : Worker(context,
    workerParameters) {
    override fun doWork(): Result {
        val imagePath = inputData.getString("image_path") ?: return Result.failure()
        Log.d("Debug", "Sepia" + imagePath)
        val bitmap = BitmapFactory.decodeFile(imagePath)
        val newBitmap = ImageUtils.applySepiaFilter(bitmap)

        val outputStream = FileOutputStream(imagePath)
        outputStream.use { output ->
            newBitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
            output.flush()
        }

        val output = workDataOf("image_path" to imagePath)

        // return with the filtered image path(same as original image path)
        return Result.success(output)
    }
}
