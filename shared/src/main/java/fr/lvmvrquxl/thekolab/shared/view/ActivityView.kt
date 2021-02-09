package fr.lvmvrquxl.thekolab.shared.view

import android.view.View
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding

/**
 * Base class of all views.
 *
 * This abstract class is the parent of all views in the application.
 * According to the *Model-View-Presenter* design pattern, a view is **ONLY** responsible for
 * displaying data. The views of the application are following this pattern.
 *
 * @param VB View binding's type
 *
 * @since 1.0.0
 *
 * @see [ViewBinding]
 */
abstract class ActivityView<VB : ViewBinding> {
    /**
     * Root of the view.
     *
     * @since 1.0.0
     *
     * @see [View]
     */
    val root: View?
        get(): View? = this.viewBinding?.root

    /**
     * View binding.
     *
     * @since 1.0.0
     */
    protected var viewBinding: VB? = null

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
    @CallSuper
    open fun onDestroy() {
        this.viewBinding = null
    }

    /**
     * Callback when the view is pausing.
     *
     * @since 1.0.0
     */
    open fun onPause(): Unit? = null

    /**
     * Callback when the view is requesting permissions.
     *
     * @since 1.0.0
     */
    open fun onRequestPermissionsResult(grantResults: IntArray) {}

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
    open fun onStop(): Unit? = null
}