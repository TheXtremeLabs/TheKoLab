package fr.lvmvrquxl.thekolab.splashscreen.view.app_name

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.Runnable

// TODO: Add documentation
internal class AppNameEndView private constructor(
    activity: AppCompatActivity,
    view: MaterialTextView
) : AppNameView(activity, view) {
    companion object {
        // TODO: Add documentation
        fun create(activity: AppCompatActivity, view: MaterialTextView): AppNameView =
            AppNameEndView(activity, view)
    }

    override val startAnimation: Runnable
        get() = super.animation.apply {
            this.longDuration()
            this.translationXBy(TRANSLATION_X)
            this.onEnd { super.viewModel.showVersionName() }
        }

    override fun onResume() {
        super.onResume()
        super.translationX(-TRANSLATION_X)
    }
}