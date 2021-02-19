package fr.lvmvrquxl.thekolab.shared.view

abstract class LifecycleView {
    /**
     * Callback when the view is creating.
     *
     * @since 1.0.0
     */
    open fun onCreate() {}

    /**
     * Callback when the view is destroying.
     *
     * @since 1.0.0
     */
    open fun onDestroy() {}

    /**
     * Callback when the view is pausing.
     *
     * @since 1.0.0
     */
    open fun onPause(): Unit? = null

    /**
     * Callback when the view is resuming.
     *
     * @since 1.0.0
     */
    open fun onResume(): Unit? = null

    /**
     * Callback when the view is starting.
     *
     * @since 1.0.0
     */
    open fun onStart() {}

    /**
     * Callback when the view is stopping.
     *
     * @since 1.0.0
     */
    open fun onStop() {}
}