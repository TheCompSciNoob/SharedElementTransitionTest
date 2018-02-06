package com.example.per6.sharedelementtransitiontest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.animal_selector_fragment.*
const val ANIMAL_ID_KEY = "animal id key"

/**
 * Created by per6 on 2/6/18.
 */
class AnimalSelectorFragment : Fragment() {

    private val animalImages = arrayListOf<Int>(R.mipmap.alligator, R.mipmap.anaconda, R.mipmap.angelfish, R.mipmap.ant,
            R.mipmap.anteater, R.mipmap.antelope, R.mipmap.apatosaurus, R.mipmap.atlas_beetle,
            R.mipmap.baboon, R.mipmap.bass, R.mipmap.bat, R.mipmap.bear)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.animal_selector_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animalSelectorRecyclerView.layoutManager = GridLayoutManager(context, 2)
        animalSelectorRecyclerView.adapter = AnimalAdapter(animalImages, object : AnimalAdapter.AnimalOnItemClickListener {
            override fun onItemClick(position: Int) {

                val bundle = Bundle()
                bundle.putInt(ANIMAL_ID_KEY, animalImages[position])
                val animalViewerFragment = AnimalViewerFragment()
                animalViewerFragment.arguments = bundle
                activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.display_frame, animalViewerFragment)
                        .addToBackStack(null)
                        .commit()
            }
        })
    }
}