package fr.lvmvrquxl.thekolab.splashscreen.view.version

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel
import kotlinx.coroutines.Runnable

/**
 * View of the layout's root responsible for displaying the application's version in
 * the splashscreen's activity.
 *
 * @since 2.0.0
 */
internal class VersionRootView private constructor(
    private val activityReference: ActivityReference,
    view: ConstraintLayout
) : AnimatedView(activityReference, view) {
    companion object {
        private const val CLOSE_ACTIVITY_DELAY: Long = 750
        private const val TRANSLATION_Y: Float = 64f

        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the splashscreen's activity
         * @param view View corresponding to the layout's root
         *
         * @since 2.0.0
         */
        fun observe(activityReference: ActivityReference, view: ConstraintLayout) =
            VersionRootView(activityReference, view).let { v: VersionRootView ->
                activityReference.get()?.addObserver(v)
            }
    }

    override val startAnimation: Runnable
        get() = super.animation.apply {
            this.longDuration()
            this.translationYBy(TRANSLATION_Y)
            this.onEnd { this@VersionRootView.closeActivity() }
        }

    private val stateObserver: Observer<SplashscreenState>
        get() = Observer { state: SplashscreenState ->
            if (SplashscreenState.SHOW_VERSION_NAME == state) super.showStartAnimation()
        }

    private var viewModel: SplashscreenViewModel? = null

    override fun onCreate() {
        this.viewModel = SplashscreenViewModel.instance.apply {
            this@VersionRootView.activityReference.get()?.let { activity: Activity ->
                this.state.observe(activity, this@VersionRootView.stateObserver)
            }
        }
    }

    override fun onDestroy() {
        this.viewModel = null
        this.activityReference.get()?.removeObserver(this)
        super.onDestroy()
    }

    override fun onResume() {
        super.hide()
        super.translationY(-TRANSLATION_Y)
    }

    private fun closeActivity() = super.animation.apply {
        this.delay(CLOSE_ACTIVITY_DELAY)
        this.onStart { this@VersionRootView.viewModel?.closeActivity() }
    }.run()
}