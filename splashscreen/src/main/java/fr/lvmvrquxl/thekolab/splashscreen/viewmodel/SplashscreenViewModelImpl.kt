package fr.lvmvrquxl.thekolab.splashscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// TODO: Add documentation
internal object SplashscreenViewModelImpl : SplashscreenViewModel() {
    override val state: LiveData<SplashscreenState>
        get() = this.stateData

    private val stateData: MutableLiveData<SplashscreenState> = MutableLiveData()

    private var stateManager: SplashscreenStateManager? = null

    override fun destroyActivity() {
        this.stateManager?.closable()
        this.syncState()
    }

    override fun onCleared() {
        this.stateManager?.clear()
        this.stateManager = null
    }

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
        this.stateManager?.showAppName()
        this.syncState()
    }

    override fun showVersionName() {
        this.stateManager?.showVersionName()
        this.syncState()
    }

    private fun createStateManager() {
        this.stateManager = SplashscreenStateManager.create().apply { this.onCreate() }
    }

    private fun destroyStateManager() = this.stateManager?.onDestroy()

    private fun pauseStateManager() = this.stateManager?.onPause()

    private fun resumeStateManager() = this.stateManager?.onResume()

    private fun showLogo() {
        this.stateManager?.showLogo()
        this.syncState()
    }

    private fun startStateManager() = this.stateManager?.onStart()

    private fun stopStateManager() = this.stateManager?.onStop()

    private fun syncState() =
        this.stateManager?.state?.let { state: SplashscreenState -> this.stateData.value = state }
}