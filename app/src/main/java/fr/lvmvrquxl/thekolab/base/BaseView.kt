package fr.lvmvrquxl.thekolab.base

import android.view.View
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
 * @since 0.1.3
 */
abstract class BaseView<VB : ViewBinding> {
    /**
     * Root of the view.
     *
     * This read-only property is the root of the view binding.
     *
     * @since 0.1.3
     * @see [viewBinding]
     */
    val root: View?
        get(): View? = this.viewBinding?.root

    /**
     * View binding.
     *
     * This protected field should be used by child views for linking code with different elements
     * of an activity or a fragment.
     * It is recommended to set this field when you are creating your class (ex : in `init` method).
     * After setting this field, you will be able to access directly to the root element of this
     * view simply by using the [root] property.
     *
     * @since 0.1.3
     * @see [root]
     */
    protected var viewBinding: VB? = null

    /**
     * Destroy the view.
     *
     * Destroy the current view binding setting it to `null`.
     * For reading purpose, this method should be called from views manipulating elements of an
     * activity.
     * If your view is manipulating elements of a fragment, consider using [onDestroyView] instead.
     *
     * @since 0.1.3
     * @see [onDestroyView]
     */
    open fun onDestroy() {
        this.viewBinding = null
    }

    /**
     * Destroy the view.
     *
     * Destroy the current view binding setting it to `null`.
     * For reading purpose, this method should be called from views manipulating elements of a
     * fragment.
     * If your view is manipulating elements of an activity, consider using [onDestroy] instead.
     *
     * @since 0.1.3
     * @see onDestroy
     */
    open fun onDestroyView() = this.onDestroy()

    /**
     * Pause the view.
     *
     * This method only exists for following the lifecycle of activities and fragments of
     * Android applications.
     * It has no implementation in [BaseView], but could be implemented in child views for
     * background tasks, like canceling a coroutine.
     *
     * @since 0.1.3
     */
    open fun onPause(): Unit? = null

    /**
     * Resume the view.
     *
     * This method only exists for following the lifecycle of activities and fragments of
     * Android applications.
     * It has no implementation in [BaseView], but could be implemented in child views for
     * background tasks, like starting a coroutine.
     *
     * @since 0.1.3
     */
    open fun onResume(): Unit? = null
}