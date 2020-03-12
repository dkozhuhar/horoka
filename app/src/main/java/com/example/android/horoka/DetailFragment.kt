package com.example.android.horoka

import android.animation.ObjectAnimator
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import androidx.work.WorkManager
import com.example.android.horoka.databinding.FragmentDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private val detailFragmentJob = Job()

    //        get arguments from navigations safeargs
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var viewModel: HorokaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get instance of a viewmodel
//        viewModel =
//            ViewModelProviders.of(this, HorokaViewModel.Factory(this.activity!!.application))
//                .get(HorokaViewModel::class.java)
        viewModel = HorokaViewModel(this.activity!!.application)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        inflating binding
        val binding = FragmentDetailBinding.inflate(inflater)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.detailView.transitionName = args.imageId

//        Setting FAB animation
        val displayDensity = Resources.getSystem().displayMetrics.density
        val fabHeightPx = 24 * displayDensity
        val fromPx = 32 * displayDensity + fabHeightPx

        ObjectAnimator.ofFloat(binding.fab,"translationY", fromPx , 0f).apply {
            this.interpolator = DecelerateInterpolator(1f)
            start()
        }

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
