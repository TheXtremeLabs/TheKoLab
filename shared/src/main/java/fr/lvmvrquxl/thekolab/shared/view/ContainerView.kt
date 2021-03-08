package fr.lvmvrquxl.thekolab.shared.view

import androidx.annotation.CallSuper

/**
 * Parent of all views that contains other views.
 *
 * @since 1.0.0
 */
abstract class ContainerView : LifecycleView() {
    @CallSuper
    override fun onCreate() = this.registerViews()

    protected open fun registerViews() {
    }
}