package com.example.android.horoka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)

        val viewModel : horokaViewModel = ViewModelProviders.of(this).get(horokaViewModel::class.java)

        val button = findViewById<View>(R.id.button)
        button.setOnClickListener {
            viewModel.notifyMe(this)
        }

    }
}
