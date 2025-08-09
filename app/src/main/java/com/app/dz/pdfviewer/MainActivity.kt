package com.app.dz.pdfviewer

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.app.dz.dzpdfviewer.HeaderData
import com.app.dz.dzpdfviewer.PdfRendererView
import com.app.dz.dzpdfviewer.util.CacheStrategy
import com.app.dz.pdfviewer.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.pdfView.isHorizontalScroll = true
        binding.pdfView.enableSideLabel = true

        /*binding.pdfView.initWithUrl(
            "https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf",
            HeaderData(), lifecycleScope, lifecycle, CacheStrategy.MAXIMIZE_PERFORMANCE
        )*/

        val file:File = getFile(
            "FrenchQuran.pdf",
            context = applicationContext,
        )

        binding.pdfView.statusListener = object : PdfRendererView.StatusCallBack {
            override fun onPdfRenderStart() {
                Log.i("pdfLog","onPdfRenderStart ");
            }
            override fun onPdfLoadStart() {
                Log.i("pdfLog","onPdfLoadStart ");
            }
            override fun onPdfLoadProgress(
                progress: Int,
                downloadedBytes: Long,
                totalBytes: Long?
            ) {
                Log.i("pdfLog","onPdfLoadProgress ");
            }
            override fun onPdfLoadSuccess(absolutePath: String) {
                Log.i("pdfLog","onPdfLoadSuccess ");
            }
            override fun onPdfRenderSuccess() {
                Log.i("pdfLog","onPdfRenderSuccess ");
            }
            override fun onError(error: Throwable) {
                Log.e("pdfLog","onError ",error);
            }
        }

        //loadPdfFromAssets("test.pdf")

    }

    fun loadPdfFromAssets(assetName: String) {
        // Copy PDF from assets to cache folder
        val tempFile = File(cacheDir, assetName)
        assets.open(assetName).use { inputStream ->
            FileOutputStream(tempFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        // Now use it with your existing initWithFile method
        binding.pdfView.initWithFile(tempFile, CacheStrategy.MAXIMIZE_PERFORMANCE)
    }


    fun getFile(filename: String?, context: Context): File {
        return File(context.filesDir, "Kounoz" + File.separator + filename)
    }
}