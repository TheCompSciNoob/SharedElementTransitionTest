package com.example.per6.sharedelementtransitiontest

import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recycler_view_image_layout.view.*

/**
 * Created by per6 on 2/6/18.
 */
class AnimalViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(imgRes : Int, onClickListener: View.OnClickListener) = with(itemView) {
        itemView.animalImageView.transitionName = "${imgRes}"
        itemView.setOnClickListener(onClickListener)
        Glide.with(context)
                .load(imgRes)
                .into(itemView.animalImageView)
    }

}