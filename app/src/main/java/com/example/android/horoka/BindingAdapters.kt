package com.example.android.horoka

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import timber.log.Timber

@BindingAdapter("img_src")
fun urlBind(imageView: ImageView, imgUrl: String?){

    imgUrl?.let {it.plus("&fm=jpg&crop=entropy&cs=tinysrgb&w=").plus(imageView.width.toString())
        it.toUri().buildUpon().scheme("https").build()
        Timber.i(it)
        Glide.with(imageView.context).load(it).into(imageView)
    }
}

@BindingAdapter("mOnClickListener")
fun mOnClickListener(view: ImageView, imageId: String) {
    val navController = Navigation.findNavController(view.)
    val action = OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(imageId)
    view.setOnClickListener {
        it.findNavController().navigate(action)
    }
}