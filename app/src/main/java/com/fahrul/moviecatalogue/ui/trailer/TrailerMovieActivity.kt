package com.fahrul.moviecatalogue.ui.trailer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fahrul.moviecatalogue.R
import com.fahrul.moviecatalogue.databinding.ActivityTrailerMovieBinding

@SuppressLint("SetJavaScriptEnabled")
class TrailerMovieActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TRAILER_PATH = "extra_trailer_path"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer_movie)

        val trailerMovieBinding = ActivityTrailerMovieBinding.inflate(layoutInflater)
        setContentView(trailerMovieBinding.root)

        val toolbar = trailerMovieBinding.toolbar
        setSupportActionBar(toolbar)

        val webView = trailerMovieBinding.webView
        val extras = intent.extras
        if (extras != null){
            val trailerPath = extras.getString(EXTRA_TRAILER_PATH)
            if (trailerPath != null){
                webView.settings.javaScriptEnabled = true
                webView.loadUrl(getString(R.string.base_path_youtube, trailerPath))
            }
        }

    }
}