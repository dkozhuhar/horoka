package com.example.android.horoka

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)

        val viewModel : horokaViewModel = ViewModelProviders.of(this).get(horokaViewModel::class.java)

        val imageView = findViewById<ImageView>(R.id.image)
        imageView.setOnClickListener {
            viewModel.notifyMe(this)
        }
        Glide.with(this).load(Uri.parse("https://images.unsplash.com/photo-1558980664-10e7170b5df9")).into(imageView)
//        imageView.setImageURI(Uri.parse("https://images.unsplash.com/photo-1558980664-10e7170b5df9"))
    }
}
//https://images.unsplash.com/photo-1525206809752-65312b959c88
//https://images.unsplash.com/photo-1496429946712-acb085074b51
//https://images.unsplash.com/photo-1516967124798-10656f7dca28
//https://images.unsplash.com/photo-1508257082719-44cae0c0044d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80
//https://images.unsplash.com/photo-1449495169669-7b118f960251?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1951&q=80
//https://images.unsplash.com/photo-1508662072197-438d38a81a97?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80
//https://images.unsplash.com/photo-1494247872528-c25b4623cf0d?ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80
//https://images.unsplash.com/photo-1518541511379-fb7260465800?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80
//https://images.unsplash.com/photo-1425421543490-6a133856ff32?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80
//https://images.unsplash.com/photo-1473082538761-d4c7cd3f5e91?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2112&q=80