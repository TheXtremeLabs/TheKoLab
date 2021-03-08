package fr.lvmvrquxl.thekolab.shared.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Interface responsible for observing activities lifecycle.
 *
 * It could be implemented by views, view models, or other components that reacts to
 * the lifecycle of the current activity.
 *
 * @since 2.0.0
 */
interface LifecycleObserver : LifecycleObserver {
    /**
     * Callback when the view is creating.
     *
     * @since 2.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()

    /**
     * Callback when the view is destroying.
     *
     * @since 2.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()

    /**
     * Callback when the view is pausing.
     *
     * @since 2.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()

    /**
     * Callback when the view is resuming.
     *
     * @since 2.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()

    /**
     * Callback when the view is starting.
     *
     * @since 2.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    /**
     * Callback when the view is stopping.
     *
     * @since 2.0.0
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()
}