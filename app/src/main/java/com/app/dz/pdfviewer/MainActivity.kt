package com.app.dz.pdfviewer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.app.dz.dzpdfviewer.HeaderData
import com.app.dz.dzpdfviewer.util.CacheStrategy
import com.app.dz.pdfviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.pdfView.isHorizontalScroll = true
        binding.pdfView.enableSideLabel = true

        binding.pdfView.initWithUrl(
            "https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf",
            HeaderData(), lifecycleScope, lifecycle, CacheStrategy.MAXIMIZE_PERFORMANCE
        )


    }
}