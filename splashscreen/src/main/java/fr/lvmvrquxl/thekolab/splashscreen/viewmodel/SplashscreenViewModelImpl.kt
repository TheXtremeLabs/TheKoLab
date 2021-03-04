package fr.lvmvrquxl.thekolab.splashscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

// TODO: Add documentation
internal object SplashscreenViewModelImpl : SplashscreenViewModel() {
    override val state: LiveData<SplashscreenState>
        get() = this.stateData

    private val stateData: MutableLiveData<SplashscreenState> = MutableLiveData()
    private var currentState: SplashscreenState? = null

    override fun destroyActivity() {
        this.setCurrentStateToClosable()
        this.syncState()
    }

    override fun onDestroy() = this.destroyCurrentState()

    override fun onStart() {
        this.setCurrentStateToStart()
        this.syncState()
        this.setCurrentStateToShowLogo()
        this.syncState()
    }

    override fun onStop() {
        this.setCurrentStateToStop()
        this.syncState()
    }

    override fun showAppName() {
        this.setCurrentStateToShowAppName()
        this.syncState()
    }

    override fun showVersionName() {
        this.setCurrentStateToShowVersionName()
        this.syncState()
    }

    private fun destroyCurrentState() = runBlocking(Dispatchers.Default) {
        this@SplashscreenViewModelImpl.currentState = null
    }

    private fun setCurrentStateToClosable() = runBlocking(Dispatchers.Default) {
        this@SplashscreenViewModelImpl.currentState = SplashscreenState.CLOSABLE
    }

    private fun setCurrentStateToShowAppName() = runBlocking(Dispatchers.Default) {
        this@SplashscreenViewModelImpl.currentState = SplashscreenState.SHOW_APP_NAME
    }

    private fun setCurrentStateToShowLogo() = runBlocking(Dispatchers.Default) {
        this@SplashscreenViewModelImpl.currentState = SplashscreenState.SHOW_LOGO
    }

    private fun setCurrentStateToShowVersionName() = runBlocking(Dispatchers.Default) {
        this@SplashscreenViewModelImpl.currentState = SplashscreenState.SHOW_VERSION_NAME
    }

    private fun setCurrentStateToStart() = runBlocking(Dispatchers.Default) {
        this@SplashscreenViewModelImpl.currentState = SplashscreenState.START
    }

    private fun setCurrentStateToStop() = runBlocking(Dispatchers.Default) {
        this@SplashscreenViewModelImpl.currentState = SplashscreenState.STOP
    }

    private fun syncState() {
        this.stateData.value = this.currentState
    }
}