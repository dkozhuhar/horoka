package com.example.android.horoka

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.horoka.db.HorokaDb
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val screenWidth = displayMetrics.widthPixels


        val viewModel : HorokaViewModel = ViewModelProviders.of(this,HorokaViewModel.Factory(application)).get(HorokaViewModel::class.java)
        button.setOnClickListener { viewModel.getPhotosFromUnsplash() }
        main_recycler_view.setHasFixedSize(true)
        main_recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel.horokaPhotos.observe(this, Observer {
            main_recycler_view.adapter = PhotoAdapter(it, screenWidth/2)
        })


    }
}
