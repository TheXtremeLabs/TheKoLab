package fr.lvmvrquxl.thekolab.splashscreen.viewmodel

import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.viewmodel.ViewModel

/**
 * View model of the splashscreen's activity.
 *
 * @since 2.0.0
 */
internal abstract class SplashscreenViewModel : ViewModel() {
    companion object {
        /**
         * Instance of the view model.
         *
         * @since 2.0.0
         */
        val instance: SplashscreenViewModel = SplashscreenViewModelImpl
    }

    /**
     * State of the activity.
     *
     * @since 2.0.0
     */
    abstract val state: LiveData<SplashscreenState>

    private var activity: Activity? = null

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    /**
     * Show the application's name in the activity.
     *
     * @since 2.0.0
     */
    abstract fun showAppName()

    /**
     * Show the application's version name in the activity.
     *
     * @since 2.0.0
     */
    abstract fun showVersionName()

    /**
     * Observe the given activity's lifecycle.
     *
     * @param activity Splashscreen's activity
     *
     * @since 2.0.0
     */
    fun observe(activity: Activity) =
        activity.apply { this@SplashscreenViewModel.activity = this }.addObserver(this)

    private fun stopActivityObservation() = this.activity?.removeObserver(this)
}