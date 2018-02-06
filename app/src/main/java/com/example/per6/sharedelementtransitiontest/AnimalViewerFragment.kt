package com.example.per6.sharedelementtransitiontest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.animal_viewer_fragment.*

/**
 * Created by per6 on 2/6/18.
 */
class AnimalViewerFragment : Fragment() {

    private var imageResource : Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.animal_viewer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageResource = arguments!!.getInt(ANIMAL_ID_KEY)
        animalDetailImageView.setImageResource(imageResource)
    }
}