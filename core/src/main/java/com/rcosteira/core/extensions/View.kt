package com.rcosteira.core.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

/** Since it's a simple use case, we can isolate Glide in an extension function. On more complicated cases, we would
 *  probably need a dedicated class.
 */
fun ImageView.load(imageAddress: String) {
    Glide.with(context)
        .load(imageAddress)
        .into(this)
}