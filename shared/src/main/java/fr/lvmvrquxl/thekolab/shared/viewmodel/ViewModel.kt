package fr.lvmvrquxl.thekolab.shared.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.shared.view.LifecycleObserver

/**
 * Parent of the application's view models.
 */
abstract class ViewModel : ViewModel(), LifecycleObserver {
    override fun onCreate() {}

    @CallSuper
    override fun onDestroy() = this.onCleared()

    override fun onPause() {}

    override fun onResume() {}

    override fun onStart() {}

    override fun onStop() {}

    /**
     * Close the activity.
     */
    open fun closeActivity() {}

    /**
     * Callback when the user click on the back button of the navigation bar.
     */
    open fun onBackPressed() {}
}