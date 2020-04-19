package com.araujoraul.desafioandroid.extension

import com.araujoraul.desafioandroid.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

fun CircleImageView.loadImage(imageUrl: String?) {
    Glide.with(this.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_account_circle_black_24dp)
            .into(this)
}