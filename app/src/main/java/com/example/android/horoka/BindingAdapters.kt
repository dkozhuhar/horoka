package com.example.android.horoka

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import timber.log.Timber

@BindingAdapter("img_src")
fun urlBind(imageView: ImageView, imgUrl: String?){
    imgUrl?.let {
        it.toUri().buildUpon().scheme("https").build()
        Timber.i(it)
        Glide.with(imageView.context).load(it).into(imageView)
    }
}