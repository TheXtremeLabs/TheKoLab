package fr.lvmvrquxl.thekolab.shared.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import fr.lvmvrquxl.thekolab.shared.view.ILifecycle

// TODO: Add documentation
abstract class ViewModel : ViewModel(), ILifecycle, LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
    }

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