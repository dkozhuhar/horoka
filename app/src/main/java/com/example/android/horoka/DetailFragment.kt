package com.example.android.horoka

import android.animation.ObjectAnimator
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.work.WorkManager
import com.example.android.horoka.databinding.FragmentDetailBinding

import kotlinx.coroutines.*
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    val detailFragmentJob = Job()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//get instance of a viewmodel
        val viewModel: HorokaViewModel =
            ViewModelProviders.of(this, HorokaViewModel.Factory(this.activity!!.application))
                .get(HorokaViewModel::class.java)

//        inflating binding
        val binding = FragmentDetailBinding.inflate(inflater)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

//        Setting FAB animation
        val displayDensity = Resources.getSystem().displayMetrics.density
        val fabHeightPx = 24 * displayDensity
        val fromPx = Resources.getSystem().displayMetrics.heightPixels.toFloat() + fabHeightPx

        ObjectAnimator.ofFloat(binding.fab,"translationY", fromPx , 0f).apply {
            this.interpolator = DecelerateInterpolator(1f)
            start()
        }

//        get arguments from navigations safeargs
        val args: DetailFragmentArgs by navArgs()

//        Setting coroutine
        val detailFragmentScope = CoroutineScope(Dispatchers.Main + detailFragmentJob)

//      getting horokaPhoto from viewmodel using arguments async
        val horokaPhoto = detailFragmentScope.launch {
            binding.horokaPhoto = viewModel.getPhotoById(args.imageId)
            binding.executePendingBindings()
        }


        // Inflate the layout for this fragment

        val workInfo = WorkManager.getInstance(this.context!!).getWorkInfosForUniqueWork("GET_LOVE_EVERYDAY").get()[0].state
        Timber.i(workInfo.toString())

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailFragmentJob.cancel()
    }
}
