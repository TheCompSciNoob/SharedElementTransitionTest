package com.example.per6.sharedelementtransitiontest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by per6 on 2/6/18.
 */
class AnimalAdapter(val imageResources : List<Int>, val listener: AnimalOnItemClickListener) : RecyclerView.Adapter<AnimalViewHolder>() {

    override fun getItemCount(): Int {
        return imageResources.size
    }

    override fun onBindViewHolder(holder: AnimalViewHolder?, position: Int) {
        holder?.bind(imageResources[position], View.OnClickListener {
            listener.onItemClick(position)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val rootView = inflater.inflate(R.layout.recycler_view_image_layout, parent, false)
        return AnimalViewHolder(rootView)
    }

    interface AnimalOnItemClickListener {

        fun onItemClick(position : Int)
    }
}