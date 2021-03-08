package fr.lvmvrquxl.thekolab.splashscreen.viewmodel

import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * Manager of the splashscreen activity's state.
 *
 * @since 2.0.0
 */
internal class SplashscreenStateManager private constructor() : LifecycleView() {
    companion object {
        /**
         * Create an instance of the state manager.
         *
         * @return New instance of the manager
         *
         * @since 2.0.0
         */
        fun create(): SplashscreenStateManager = SplashscreenStateManager()
    }

    /**
     * Current state of the activity.
     *
     * @since 2.0.0
     */
    val state: SplashscreenState?
        get() = this.currentState

    private var currentState: SplashscreenState? = null

    override fun onCreate() = this.setCurrentState(SplashscreenState.CREATE)

    override fun onDestroy() = this.setCurrentState(SplashscreenState.DESTROY)

    override fun onPause() = this.setCurrentState(SplashscreenState.PAUSE)

    override fun onResume() = this.setCurrentState(SplashscreenState.RESUME)

    override fun onStart() = this.setCurrentState(SplashscreenState.START)

    override fun onStop() = this.setCurrentState(SplashscreenState.STOP)

    /**
     * Clear the current state of the activity.
     *
     * @since 2.0.0
     */
    fun clear() {
        this.currentState = null
    }

    /**
     * Set the activity's state to close.
     *
     * @since 2.0.0
     */
    fun close() = this.setCurrentState(SplashscreenState.CLOSE)

    /**
     * Set the activity's state to show the application's name.
     *
     * @since 2.0.0
     */
    fun showAppName() = this.setCurrentState(SplashscreenState.SHOW_APP_NAME)

    /**
     * Set the activity's state to show the application's logo.
     *
     * @since 2.0.0
     */
    fun showLogo() = this.setCurrentState(SplashscreenState.SHOW_LOGO)

    /**
     * Set the activity's state to show the application's version name.
     *
     * @since 2.0.0
     */
    fun showVersionName() = this.setCurrentState(SplashscreenState.SHOW_VERSION_NAME)

    private fun setCurrentState(state: SplashscreenState) = runBlocking(Dispatchers.Default) {
        this@SplashscreenStateManager.currentState = state
    }
}