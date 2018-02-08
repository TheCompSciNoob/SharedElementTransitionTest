package com.example.per6.sharedelementtransitiontest

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import kotlinx.android.synthetic.main.animal_selector_fragment.*
const val ANIMAL_ID_KEY = "animal id key"
const val IMAGE_VIEW_TRANSITION_NAME = "image view transition name"

/**
 * Created by per6 on 2/6/18.
 */
class AnimalSelectorFragment : Fragment() {

    private val animalImages = arrayListOf<Int>(R.mipmap.alligator, R.mipmap.anaconda, R.mipmap.angelfish, R.mipmap.ant,
            R.mipmap.anteater, R.mipmap.antelope, R.mipmap.apatosaurus, R.mipmap.atlas_beetle,
            R.mipmap.baboon, R.mipmap.bass, R.mipmap.bat, R.mipmap.bear)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        enterTransition = Fade()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.animal_selector_fragment, container, false)
        rootView.viewTreeObserver.addOnGlobalLayoutListener(StartPostponed(this))
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animalSelectorRecyclerView.layoutManager = GridLayoutManager(context, 2)
        animalSelectorRecyclerView.adapter = AnimalAdapter(animalImages, object : AnimalAdapter.AnimalOnItemClickListener {
            override fun onItemClick(clickedImageView: ImageView, position : Int) {

                val bundle = Bundle()
                bundle.putInt(ANIMAL_ID_KEY, animalImages[position])
                bundle.putString(IMAGE_VIEW_TRANSITION_NAME, ViewCompat.getTransitionName(clickedImageView))
                val animalViewerFragment = AnimalViewerFragment()
                animalViewerFragment.arguments = bundle
                activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.display_frame, animalViewerFragment)
                        .addSharedElement(clickedImageView, ViewCompat.getTransitionName(clickedImageView))
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit()
            }
        })
    }

    class StartPostponed(private val fragment: Fragment): ViewTreeObserver.OnGlobalLayoutListener {

        override fun onGlobalLayout() {
            fragment.startPostponedEnterTransition()
            fragment.view?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
        }

    }
}