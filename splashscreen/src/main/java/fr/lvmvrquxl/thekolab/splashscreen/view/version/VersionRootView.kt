package fr.lvmvrquxl.thekolab.splashscreen.view.version

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel
import kotlinx.coroutines.Runnable

/**
 * View of the layout's root responsible for displaying the application's version in
 * the splashscreen's activity.
 *
 * @since 1.1.0
 */
internal class VersionRootView private constructor(
    private val activity: Activity,
    view: ConstraintLayout
) : AnimatedView(activity, view) {
    companion object {
        private const val DESTROY_ACTIVITY_DELAY: Long = 750
        private const val TRANSLATION_Y: Float = 64f

        /**
         * Observe the given activity's lifecycle.
         *
         * @param activity Splashscreen's activity
         * @param view View corresponding to the layout's root
         *
         * @since 1.1.0
         */
        fun observe(activity: Activity, view: ConstraintLayout) =
            VersionRootView(activity, view).let { v: VersionRootView ->
                activity.addObserver(v)
            }
    }

    override val startAnimation: Runnable
        get() = super.animation.apply {
            this.longDuration()
            this.translationYBy(TRANSLATION_Y)
            this.onEnd { this@VersionRootView.destroyActivity() }
        }

    private val stateObserver: Observer<SplashscreenState>
        get() = Observer { state: SplashscreenState ->
            if (SplashscreenState.SHOW_VERSION_NAME == state) super.showStartAnimation()
        }

    private var viewModel: SplashscreenViewModel? = null

    override fun onCreate() {
        super.onCreate()
        this.viewModel = SplashscreenViewModel.instance.apply {
            this.state.observe(this@VersionRootView.activity, this@VersionRootView.stateObserver)
        }
    }

    override fun onDestroy() {
        this.viewModel = null
        this.activity.removeObserver(this)
        super.onDestroy()
    }

    override fun onResume() {
        super.hide()
        super.translationY(-TRANSLATION_Y)
    }

    private fun destroyActivity() = super.animation.apply {
        this.delay(DESTROY_ACTIVITY_DELAY)
        this.onStart { this@VersionRootView.viewModel?.destroyActivity() }
    }.run()
}