package com.example.per6.sharedelementtransitiontest

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.animal_viewer_fragment.*

/**
 * Created by per6 on 2/6/18.
 */
class AnimalViewerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        enterTransition = Fade()
        sharedElementEnterTransition = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.animal_viewer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animalDetailImageView.transitionName = arguments!!.getString(IMAGE_VIEW_TRANSITION_NAME, null)
        Glide.with(this)
                .load(arguments!!.getInt(ANIMAL_ID_KEY))
                /*.listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        throw e!!
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        Log.d("AnimalViewerFragment", "resource ready")
                        startPostponedEnterTransition()
                        return false
                    }

                })*/
                .into(animalDetailImageView)
        view.viewTreeObserver.addOnGlobalLayoutListener(AnimalSelectorFragment.StartPostponed(this))
    }
}