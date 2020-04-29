package com.example.android.horoka

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
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
        this.requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        val screenWidth = displayMetrics.widthPixels

        val viewModel = HorokaViewModel(this.requireActivity().application)


        view.main_recycler_view.setHasFixedSize(true)
        view.main_recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        val onItemClickListener : (ItemPhotoBinding) -> Unit  = {
            val photoId = it.horokaPhoto!!.id
            val extras = FragmentNavigatorExtras(
                it.itemPhotoIv to photoId
            )
            findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(photoId),extras)
        }

        viewModel.horokaPhotos.observe(viewLifecycleOwner, Observer {
            view.main_recycler_view.adapter = null
            view.main_recycler_view.layoutManager = null
            view.main_recycler_view.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            view.main_recycler_view.adapter = PhotoAdapter(it, screenWidth / 2, onItemClickListener)
//            (view.main_recycler_view.adapter as PhotoAdapter).notifyDataSetChanged()
        })

        return view
    }
}