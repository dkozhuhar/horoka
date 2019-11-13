package com.example.android.horoka

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)

//        UnsplashPhotoPicker.init(application,getString(R.string.accessKey),getString(R.string.secretKey))

//        val viewModel : horokaViewModel = ViewModelProviders.of(this).get(horokaViewModel::class.java)

        main_recycler_view.setHasFixedSize(true)
        main_recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        main_recycler_view.adapter = PhotoAdapter()
    }
}
