package com.example.android.horoka

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import timber.log.Timber
import java.io.File

@BindingAdapter("img_src", "img_id", requireAll = false)
fun urlBind(imageView: ImageView, imgUrl: String?, imgId: String?) {
//    Checking if local copy is available and taking it if exists
    val file = File(imageView.context.filesDir, imgId.plus(".jpg"))
    if (file.exists()) {
        Glide.with(imageView.context).load(file.toURI()).into(imageView)
//        If no local copy available falling back to downloading from web
    } else {
        imgUrl?.let {
            val imageWidth = imageView.width
            it.plus("&fm=jpg&crop=entropy&cs=tinysrgb&w=").plus(imageWidth.toString())
            it.toUri().buildUpon().scheme("https").build()
            Timber.i(it)
            Glide.with(imageView.context).load(it).into(imageView)
//            creating local copy
        }
    }
}
