package fr.lvmvrquxl.thekolab.shared.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

// TODO: Add documentation
interface LifecycleObserver : LifecycleObserver {
    /**
     * Callback when the view is creating.
     *
     * @since 1.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()

    /**
     * Callback when the view is destroying.
     *
     * @since 1.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()

    /**
     * Callback when the view is pausing.
     *
     * @since 1.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()

    /**
     * Callback when the view is resuming.
     *
     * @since 1.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()

    /**
     * Callback when the view is starting.
     *
     * @since 1.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    /**
     * Callback when the view is stopping.
     *
     * @since 1.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()
}