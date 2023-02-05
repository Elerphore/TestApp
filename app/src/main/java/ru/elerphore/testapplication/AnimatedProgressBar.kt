package ru.elerphore.testapplication

import android.animation.ObjectAnimator
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar

class AnimatedProgressBar(progressBar: ProgressBar) {
    private val animator: ObjectAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)

    fun fakeLoading(duration: Long = 14000) {
        animator.duration = duration
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    fun pause() = animator.pause()

    fun resume() = animator.resume()
}