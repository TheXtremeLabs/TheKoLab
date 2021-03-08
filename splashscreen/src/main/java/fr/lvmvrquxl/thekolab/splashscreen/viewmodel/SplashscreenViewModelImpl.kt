package fr.lvmvrquxl.thekolab.splashscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Implementation of the splashscreen activity's view model.
 *
 * @since 2.0.0
 */
internal object SplashscreenViewModelImpl : SplashscreenViewModel() {
    override val state: LiveData<SplashscreenState>
        get() = this.stateData

    private val stateData: MutableLiveData<SplashscreenState> = MutableLiveData()

    private var stateManager: SplashscreenStateManager? = null

    override fun destroyActivity() {
        this.closeStateManager()
        this.syncState()
    }

    override fun onCleared() = this.clearStateManager()

    override fun onCreate() {
        this.createStateManager()
        this.syncState()
    }

    override fun onDestroy() {
        this.destroyStateManager()
        this.syncState()
        super.onDestroy()
    }

    override fun onPause() {
        this.pauseStateManager()
        this.syncState()
    }

    override fun onResume() {
        this.resumeStateManager()
        this.syncState()
        this.showLogo()
    }

    override fun onStart() {
        this.startStateManager()
        this.syncState()
    }

    override fun onStop() {
        this.stopStateManager()
        this.syncState()
    }

    override fun showAppName() {
        this.showAppNameWithStateManager()
        this.syncState()
    }

    override fun showVersionName() {
        this.showVersionNameWithStateManager()
        this.syncState()
    }

    private fun clearStateManager() {
        this.stateManager?.clear()
        this.stateManager = null
    }

    private fun closeStateManager() = this.stateManager?.close()

    private fun createStateManager() {
        this.stateManager = SplashscreenStateManager.create().apply { this.onCreate() }
    }

    private fun destroyStateManager() = this.stateManager?.onDestroy()

    private fun pauseStateManager() = this.stateManager?.onPause()

    private fun resumeStateManager() = this.stateManager?.onResume()

    private fun showAppNameWithStateManager() = this.stateManager?.showAppName()

    private fun showLogo() {
        this.showLogoWithStateManager()
        this.syncState()
    }

    private fun showLogoWithStateManager() = this.stateManager?.showLogo()

    private fun showVersionNameWithStateManager() = this.stateManager?.showVersionName()

    private fun startStateManager() = this.stateManager?.onStart()

    private fun stopStateManager() = this.stateManager?.onStop()

    private fun syncState() =
        this.stateManager?.state?.let { state: SplashscreenState -> this.stateData.value = state }
}