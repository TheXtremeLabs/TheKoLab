package fr.lvmvrquxl.thekolab.shared.view

import androidx.annotation.CallSuper

/**
 * Parent of all views that contains other views.
 *
 * @since 1.0.0
 *
 * @see LifecycleView
 */
abstract class ContainerView : LifecycleView() {
    private val views: MutableList<LifecycleView> = mutableListOf()

    @CallSuper
    override fun onCreate() = this.createViews()

    @CallSuper
    override fun onDestroy() = this.destroyViews()

    @CallSuper
    override fun onStart() = this.startViews()

    /**
     * Add given view in this container.
     *
     * @param view View to add
     *
     * @since 1.0.0
     *
     * @see LifecycleView
     */
    protected fun addView(view: LifecycleView) {
        if (!this.views.contains(view)) this.views.add(view)
    }

    private fun createViews() = this.views.forEach { view: LifecycleView -> view.onCreate() }

    private fun destroyViews() {
        this.views.forEach { view: LifecycleView -> view.onDestroy() }
        this.views.clear()
    }

    private fun startViews() = this.views.forEach { view: LifecycleView -> view.onStart() }
}