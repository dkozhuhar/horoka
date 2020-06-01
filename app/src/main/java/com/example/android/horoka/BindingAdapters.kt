package com.example.android.horoka

import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import timber.log.Timber
import java.io.File

@BindingAdapter(value = ["img_src", "img_id", "img_placeholder_src"], requireAll = false)
fun urlBind(imageView: ImageView, imgUrl: String?, imgId: String?, imgPlaceholder: String?) {
//    Checking if local copy is available and using it if exists
    val file = File(imageView.context.filesDir, imgId.plus(".jpg"))
    if (file.exists()) {
        val fileUri = Uri.fromFile(file)
        Timber.v("Loading local copy ".plus(fileUri))
        imageView.setImageURI(fileUri)
//        If no local copy available falling back to downloading from web
    } else {
        imgUrl?.let {
            val placeholder = File(imageView.context.filesDir, imgPlaceholder.plus(".jpg"))
            val placeholderUri = Uri.fromFile(placeholder)
            val imageWidth = imageView.width
            it.plus("&fm=jpg&crop=entropy&cs=tinysrgb&w=").plus(imageWidth.toString())
            it.toUri().buildUpon().scheme("https").build()
            Timber.i(it)
            Glide.with(imageView.context).load(it).placeholder(Drawable.createFromPath(placeholder.absolutePath)).into(imageView)
//            creating local copy
        }
    }
}

@BindingAdapter(value = ["unsplash_username", "unsplash_user_link"], requireAll = true)
fun unsplashAttribute(textView: TextView, username:String?, userLink: String?) {
    textView.text = textView.context.getString(R.string.unsplash_attribution_string, userLink, username)
//    textView.text = "Photo by <a href=\"https://unsplash.com/@anniespratt?utm_source=your_app_name&utm_medium=referral\">Annie Spratt</a> on <a href=\"https://unsplash.com/?utm_source=your_app_name&utm_medium=referral\">Unsplash</a>"

    textView.movementMethod = LinkMovementMethod.getInstance()
}
