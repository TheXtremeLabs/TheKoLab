package fr.lvmvrquxl.thekolab.splashscreen.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.Runnable

// TODO: Add documentation
internal class AppNameEndView private constructor(
    activity: AppCompatActivity,
    private val view: MaterialTextView
) : AppNameView(activity, view) {
    companion object {
        // TODO: Add documentation
        fun create(activity: AppCompatActivity, view: MaterialTextView): AppNameView =
            AppNameEndView(activity, view)
    }

    override val startAnimation: Runnable
        get() = Runnable {
            this.view.apply {
                this.alpha = 0f
                this.translationX = -TRANSLATION_X
                this.visibility = View.VISIBLE
            }
            super.animation.apply {
                this.longDuration()
                this.translationXBy(TRANSLATION_X)
            }.run()
        }
}