package fr.lvmvrquxl.thekolab.splashscreen.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.Runnable

// TODO: Add documentation
internal class AppNameStartView private constructor(
    activity: AppCompatActivity,
    view: MaterialTextView
) : AppNameView(activity, view) {
    companion object {
        // TODO: Add documentation
        fun create(activity: AppCompatActivity, view: MaterialTextView): AppNameView =
            AppNameStartView(activity, view)
    }

    override val startAnimation: Runnable
        get() = super.animation.apply {
            this.longDuration()
            this.translationXBy(-TRANSLATION_X)
        }

    override fun onResume() {
        super.onResume()
        super.translationX(TRANSLATION_X)
    }
}