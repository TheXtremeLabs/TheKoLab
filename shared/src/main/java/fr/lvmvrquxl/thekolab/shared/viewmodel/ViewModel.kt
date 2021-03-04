package fr.lvmvrquxl.thekolab.shared.viewmodel

import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.shared.view.LifecycleObserver

// TODO: Add documentation
abstract class ViewModel : ViewModel(), LifecycleObserver {
    override fun onCreate() {}

    override fun onDestroy() {}

    override fun onPause() {}

    override fun onResume() {}

    override fun onStart() {}

    override fun onStop() {}

    /**
     * Destroy the activity.
     *
     * @since 1.0.0
     */
    abstract fun destroyActivity()

    /**
     * Callback when the user click on the back button of the navigation bar.
     *
     * @since 1.0.0
     */
    open fun onBackPressed() {}
}