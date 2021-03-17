package fr.lvmvrquxl.thekolab.splashscreen.view.app_name

import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel

/**
 * View of the application's name in the splashscreen's activity.
 *
 * @param activityReference Reference of the splashscreen's activity
 * @param view Instance of the view
 *
 * @since 2.0.0
 */
internal abstract class AppNameView(
    private val activityReference: ActivityReference,
    view: MaterialTextView
) : AnimatedView(activityReference, view) {
    companion object {
        private const val TRANSLATION_X: Float = 128f
    }

    /**
     * Value of the translation X to apply to the application's name views.
     *
     * @since 2.0.0
     */
    protected val translationX: Float
        get() = TRANSLATION_X

    private val stateObserver: Observer<SplashscreenState>
        get() = Observer { state: SplashscreenState ->
            if (SplashscreenState.SHOW_APP_NAME == state) super.showStartAnimation()
        }

    /**
     * View model of the splashscreen's activity.
     *
     * @since 2.0.0
     */
    protected var viewModel: SplashscreenViewModel? = null

    @CallSuper
    override fun onCreate() {
        this.viewModel = SplashscreenViewModel.instance.apply {
            this@AppNameView.activityReference.get()?.let { activity: Activity ->
                this.state.observe(activity, this@AppNameView.stateObserver)
            }
        }
    }

    override fun onDestroy() {
        this.viewModel = null
        this.activityReference.get()?.removeObserver(this)
        super.onDestroy()
    }

    @CallSuper
    override fun onResume() {
        super.hide()
        super.setVisible()
    }
}