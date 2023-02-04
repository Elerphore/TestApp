package ru.elerphore.testapplication.view

import android.animation.ObjectAnimator
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar


fun ProgressBar.fakeLoading() {
    val animator = ObjectAnimator.ofInt(this, "progress", 0, 100)
    animator.duration = 14000
    animator.interpolator = LinearInterpolator()
    animator.start()
}
