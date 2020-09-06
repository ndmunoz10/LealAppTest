package com.nicdamun.lealapp.extensions

import android.view.View

fun View.rotate(rotate: Boolean, duration: Long = 200L): Boolean {
    animate()
        .setDuration(duration)
        .rotation( if (rotate) 135f else 0f )
    return rotate
}

fun View.showIn(duration: Long = 200) {
    visibility = View.VISIBLE
    alpha = 0f
    translationX = height.toFloat()
    animate()
        .setDuration(duration)
        .translationX(0f)
        .alpha(1f)
        .start()
}

fun View.showOut(duration: Long = 200) {
    visibility = View.VISIBLE
    alpha = 1f
    translationX = 0f
    animate()
        .setDuration(duration)
        .translationX(height.toFloat())
        .alpha(0f)
        .start()
}