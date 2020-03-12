package com.example.android.horoka

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.horoka.databinding.ItemPhotoBinding
import com.example.android.horoka.db.HorokaPhoto
import timber.log.Timber

class PhotoAdapter(private val photos: List<HorokaPhoto>, val width: Int, val onItemCLickListener: (ItemPhotoBinding) -> Unit) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
init {
    Timber.i("Initializing")
}

//    private val mListOfPhotos = listOf(
//        "https://images.unsplash.com/photo-1525206809752-65312b959c88?w=300",
//        "https://images.unsplash.com/photo-1496429946712-acb085074b51?w=300",
//        "https://images.unsplash.com/photo-1516967124798-10656f7dca28?w=300",
//        "https://images.unsplash.com/photo-1508257082719-44cae0c0044d?w=300",
//        "https://images.unsplash.com/photo-1449495169669-7b118f960251?w=300",
//        "https://images.unsplash.com/photo-1508662072197-438d38a81a97?w=300",
//        "https://images.unsplash.com/photo-1494247872528-c25b4623cf0d?w=300",
//        "https://images.unsplash.com/photo-1518541511379-fb7260465800?w=300",
//        "https://images.unsplash.com/photo-1425421543490-6a133856ff32?w=300",
//        "https://images.unsplash.com/photo-1473082538761-d4c7cd3f5e91?w=300")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context))
//        Timber.i("creating ViewHolder")

        return PhotoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

//        Timber.e("Loading URI: " + mListOfPhotos.get(position))
        val horokaPhoto = photos[position]
        holder.binding.horokaPhoto = horokaPhoto
        holder.itemView.setOnClickListener{
            onItemCLickListener(holder.binding)
        }
        holder.binding.executePendingBindings()

    }
    //    ViewHolder for Adapter
    class PhotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)
}