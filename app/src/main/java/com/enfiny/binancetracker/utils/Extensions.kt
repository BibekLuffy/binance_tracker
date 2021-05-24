package com.enfiny.binancetracker.utils

import android.content.res.ColorStateList
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.bibekluffy.binancetracker.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


enum class SnackBarColor(val color: Int) {
    DANGER(R.color.loss),
    INFO(R.color.primary),
    SUCCESS(R.color.profit)
}

fun View.showSnackBar(string: String, type: SnackBarColor? = SnackBarColor.INFO) {
    val snackBar = Snackbar.make(this, string, Snackbar.LENGTH_SHORT)
    val layoutParams = LinearLayout.LayoutParams(snackBar.view.layoutParams)
    val color = type?.color ?: R.color.primary
    layoutParams.gravity = Gravity.TOP
    snackBar.apply {
        setBackgroundTint(color)
        setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, color)))
        view.setPadding(0, 10, 0, 0)
        view.layoutParams = layoutParams
        animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        show()
    }
}