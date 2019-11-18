package com.example.android.horoka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


        val dbDao = HorokaDb.getInstance(this).horokaDao

        val viewModel : HorokaViewModel = ViewModelProviders.of(this).get(HorokaViewModel::class.java)
        button.setOnClickListener { viewModel.getPhotosFromUnsplash(this) }
        main_recycler_view.setHasFixedSize(true)
        main_recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        main_recycler_view.adapter = PhotoAdapter()

    }
}
