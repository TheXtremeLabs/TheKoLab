package fr.lvmvrquxl.thekolab.shared.view

/**
 * Parent of all views that should follow the lifecycle of the application.
 *
 * @since 1.0.0
 */
abstract class LifecycleView : ILifecycle {
    override fun onCreate() {}

    override fun onDestroy() {}

    override fun onPause(): Unit? = null

    override fun onResume(): Unit? = null

    override fun onStart() {}

    override fun onStop() {}
}