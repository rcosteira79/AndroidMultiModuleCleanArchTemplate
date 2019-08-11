package com.rcosteira.rxjavatokotlinflows.presentation

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ImageLoader constructor(private val fragment: Fragment) {

    private lateinit var imageAddress: String

    fun load(imageAddress: String): ImageLoader {
        this.imageAddress = imageAddress
        return this
    }

    fun into(imageView: ImageView) {
        Glide.with(fragment)
            .load(imageAddress)
            .into(imageView)
    }
}