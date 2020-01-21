package com.volkanhotur.basemvvm.android.extension

import android.view.View
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.ScaleAnimation

fun View.scaleAnimateView() {
    val animation = ScaleAnimation(1.15f, 1F, 1.15f, 1F,
            RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
    this.animation = animation
    animation.duration = 100
    animation.start()
}