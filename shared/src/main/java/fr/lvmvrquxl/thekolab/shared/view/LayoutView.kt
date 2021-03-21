package fr.lvmvrquxl.thekolab.shared.view

import androidx.annotation.CallSuper

/**
 * Parent of all views corresponding to an application's layout.
 *
 * @since 2.0.0
 */
abstract class LayoutView : LifecycleView() {
    @CallSuper
    override fun onCreate() = this.registerViews()

    protected open fun registerViews() {
    }
}