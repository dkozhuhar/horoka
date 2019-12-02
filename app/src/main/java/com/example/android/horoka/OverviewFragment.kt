package com.example.android.horoka

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.horoka.databinding.ItemPhotoBinding
import kotlinx.android.synthetic.main.fragment_overview.view.*


class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val displayMetrics = DisplayMetrics()
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        this.activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)

        val screenWidth = displayMetrics.widthPixels

        val viewModel: HorokaViewModel =
            ViewModelProviders.of(this, HorokaViewModel.Factory(this.activity!!.application))
                .get(HorokaViewModel::class.java)
        view.button.setOnClickListener { viewModel.getPhotosFromUnsplash() }
        view.main_recycler_view.setHasFixedSize(true)
        view.main_recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        val onItemClickListener : (ItemPhotoBinding) -> Unit  = {
            findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it.horokaPhoto!!.id))
        }

        viewModel.horokaPhotos.observe(this, Observer {
            view.main_recycler_view.adapter = PhotoAdapter(it, screenWidth / 2, onItemClickListener)
        })

        return view
    }
}