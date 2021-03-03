package fr.lvmvrquxl.thekolab.shared.view

// TODO: Add documentation
interface ILifecycle {
    /**
     * Callback when the view is creating.
     *
     * @since 1.0.0
     */
    fun onCreate()

    /**
     * Callback when the view is destroying.
     *
     * @since 1.0.0
     */
    fun onDestroy()

    /**
     * Callback when the view is pausing.
     *
     * @since 1.0.0
     */
    fun onPause(): Unit?

    /**
     * Callback when the view is resuming.
     *
     * @since 1.0.0
     */
    fun onResume(): Unit?

    /**
     * Callback when the view is starting.
     *
     * @since 1.0.0
     */
    fun onStart()

    /**
     * Callback when the view is stopping.
     *
     * @since 1.0.0
     */
    fun onStop()
}