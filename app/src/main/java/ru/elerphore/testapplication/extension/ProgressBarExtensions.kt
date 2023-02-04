package ru.elerphore.testapplication.extension

import android.animation.ObjectAnimator
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar


fun ProgressBar.fakeLoading(duration: Long = 14000) {
    val animator = ObjectAnimator.ofInt(this, "progress", 0, 100)
    animator.duration = duration
    animator.interpolator = LinearInterpolator()
    animator.start()
}
