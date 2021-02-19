package fr.lvmvrquxl.thekolab.colors.utils

import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Runnable

internal class Animation private constructor(
    private val activity: AppCompatActivity,
    private val target: View
) : Runnable {
    companion object {
        fun animate(activity: AppCompatActivity, target: View): Animation =
            Animation(activity, target)
    }

    private val mediumDuration: Long =
        this.activity.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    private val shortDuration: Long =
        this.activity.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
    private var alpha: Float = 1f
    private var delay: Long = 0
    private var duration: Long = this.shortDuration
    private var onEnd: () -> Unit = {}

    override fun run() = this.target.animate()
        .alpha(this.alpha)
        .setDuration(this.duration)
        .setStartDelay(this.delay)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator?) {
                this@Animation.onEnd()
            }
        })
        .start()

    fun delay(delay: Long) {
        this.delay = delay
    }

    fun emptyAlpha() {
        this.alpha = 0f
    }

    fun medium() {
        this.duration = this.mediumDuration
    }

    fun onEnd(callback: () -> Unit) {
        this.onEnd = callback
    }
}