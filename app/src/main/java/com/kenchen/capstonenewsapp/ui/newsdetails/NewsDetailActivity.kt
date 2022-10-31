package com.kenchen.capstonenewsapp.ui.newsdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.work.*
import com.bumptech.glide.Glide
import com.kenchen.capstonenewsapp.databinding.ActivityNewsDetailBinding
import com.kenchen.capstonenewsapp.model.Article
import com.kenchen.capstonenewsapp.ui.MainActivity
import com.kenchen.capstonenewsapp.worker.DownloadWorker
import com.kenchen.capstonenewsapp.worker.FileClearWorker
import com.kenchen.capstonenewsapp.worker.SepiaFilterWorker

class NewsDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val article = intent.getParcelableExtra<Article>(MainActivity.INTENT_ARTICLE_KEY)!!
        title = article.title
//        binding.newsDescriptionTextView.text = article.description

        if (!article.urlToImage.isNullOrEmpty()) {
            downloadAndDisplayImage(article.urlToImage)
        }

        binding.composeView.setContent {
            DescriptionText(article.description ?: "")
        }

    }

    @Composable
    @Preview
    fun DescriptionText(description:String = "") {
        Text(
            modifier = Modifier.padding(8.dp),
            text = description,
        )
    }

    private fun downloadAndDisplayImage(imageUrl: String) {
        val constraint = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val downloadWorkerRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(constraint)
            .setInputData(workDataOf("image_url" to imageUrl))
            .build()

        val fileClearWorker = OneTimeWorkRequestBuilder<FileClearWorker>()
            .build()

        val sepiaFilterWorker = OneTimeWorkRequestBuilder<SepiaFilterWorker>()
            .setConstraints(constraint)
            .build()

        // always clear the downloaded image first (not optimal)
        val workManager = WorkManager.getInstance(this)
        workManager.beginWith(fileClearWorker)
            .then(downloadWorkerRequest)
            .then(sepiaFilterWorker)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(sepiaFilterWorker.id)
            .observe(this, Observer { info ->
                if (info.state.isFinished) {
                    // consuming image path from download worker output
                    val imagePath = info.outputData.getString("image_path")

                    // display image
                    if (!imagePath.isNullOrEmpty()) {
                        displayImage(imagePath)
                    }
                }
            })
    }

    // Display image through Glide
    private fun displayImage(imagePath: String) {
        Glide.with(this)
            .load(imagePath)
            .into(binding.newsDetailImageView)
    }
}
