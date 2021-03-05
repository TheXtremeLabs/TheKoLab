package fr.lvmvrquxl.thekolab.splashscreen.viewmodel

import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

internal class SplashscreenStateManager private constructor() : LifecycleView() {
    companion object {
        fun create(): SplashscreenStateManager = SplashscreenStateManager()
    }

    val state: SplashscreenState?
        get() = this.currentState

    private var currentState: SplashscreenState? = null

    override fun onCreate() = this.setCurrentState(SplashscreenState.CREATE)

    override fun onDestroy() = this.setCurrentState(SplashscreenState.DESTROY)

    override fun onPause() = this.setCurrentState(SplashscreenState.PAUSE)

    override fun onResume() = this.setCurrentState(SplashscreenState.RESUME)

    override fun onStart() = this.setCurrentState(SplashscreenState.START)

    override fun onStop() = this.setCurrentState(SplashscreenState.STOP)

    fun clear() {
        this.currentState = null
    }

    fun closable() = this.setCurrentState(SplashscreenState.CLOSABLE)

    fun showAppName() = this.setCurrentState(SplashscreenState.SHOW_APP_NAME)

    fun showLogo() = this.setCurrentState(SplashscreenState.SHOW_LOGO)

    fun showVersionName() = this.setCurrentState(SplashscreenState.SHOW_VERSION_NAME)

    private fun setCurrentState(state: SplashscreenState) = runBlocking(Dispatchers.Default) {
        this@SplashscreenStateManager.currentState = state
    }
}