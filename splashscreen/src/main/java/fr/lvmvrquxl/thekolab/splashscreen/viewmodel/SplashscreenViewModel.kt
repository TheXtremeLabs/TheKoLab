package fr.lvmvrquxl.thekolab.splashscreen.viewmodel

import androidx.lifecycle.LiveData
import fr.lvmvrquxl.thekolab.shared.viewmodel.ViewModel

// TODO: Add documentation
internal abstract class SplashscreenViewModel : ViewModel() {
    companion object {
        // TODO: Add documentation
        fun instance(): SplashscreenViewModel = SplashscreenViewModelImpl
    }

    // TODO: Add documentation
    abstract val state: LiveData<SplashscreenState>

    // TODO: Add documentation
    abstract fun showAppName()

    // TODO: Add documentation
    abstract fun showVersionName()

    override fun onBackPressed() {}
}