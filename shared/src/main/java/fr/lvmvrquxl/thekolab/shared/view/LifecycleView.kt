package fr.lvmvrquxl.thekolab.shared.view

/**
 * Parent of all views that should follow the lifecycle of the application.
 *
 * @since 1.0.0
 */
abstract class LifecycleView : LifecycleObserver {
    override fun onCreate() {}

    override fun onDestroy() {}

    override fun onPause() {}

    override fun onResume() {}

    override fun onStart() {}

    override fun onStop() {}
}