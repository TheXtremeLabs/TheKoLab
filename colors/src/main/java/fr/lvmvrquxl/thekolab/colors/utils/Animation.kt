package fr.lvmvrquxl.thekolab.colors.utils

import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.appcompat.app.AppCompatActivity

internal class Animation private constructor(
    private val activity: AppCompatActivity,
    private val target: View
) {
    companion object {
        fun create(activity: AppCompatActivity, target: View): Animation =
            Animation(activity, target)
    }

    private val mediumDuration: Long
        get() = this.activity.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    private val shortDuration: Long
        get() = this.activity.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

    private var alpha: Float = 1f
    private var delay: Long = 0
    private var duration: Long = this.shortDuration
    private var onEnd: () -> Unit = {}

    fun delay(delay: Long): Animation {
        this.delay = delay
        return this
    }

    fun emptyAlpha(): Animation {
        this.alpha = 0f
        return this
    }

    fun medium(): Animation {
        this.duration = this.mediumDuration
        return this
    }

    fun onEnd(callback: () -> Unit): Animation {
        this.onEnd = callback
        return this
    }

    fun start() = this.target.animate()
        .alpha(this.alpha)
        .setDuration(this.duration)
        .setStartDelay(this.delay)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator?) {
                this@Animation.onEnd()
            }
        })
        .start()
}