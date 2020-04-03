package com.araujoraul.desafioandroid.utils

import android.widget.ImageView
import com.araujoraul.desafioandroid.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(imageUrl: String?){
    Glide.with(this.context)
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(this)
}