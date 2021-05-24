package com.enfiny.binancetracker.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bibekluffy.binancetracker.R
import com.bumptech.glide.Glide


@BindingAdapter("setUrlImage")
fun loadImageUrl(view: ImageView, url:String?){
    if (url.isNullOrEmpty()) {
        Glide.with(view)
            .load(R.drawable.ic_launcher_background)
            .into(view)
    } else {
        Glide.with(view)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view)
    }
}

@BindingAdapter("setLocalImage")
fun loadImage(view: ImageView, url: Int) {
    Glide.with(view)
        .load(url)
        .into(view)
}

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleNone")
fun showNone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.INVISIBLE
}