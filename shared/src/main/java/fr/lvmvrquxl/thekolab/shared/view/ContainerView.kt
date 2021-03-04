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
    private var views: MutableList<LifecycleView>? = null

    @CallSuper
    override fun onCreate() = this.createViews()

    @CallSuper
    override fun onDestroy() = this.destroyViews()

    @CallSuper
    override fun onPause() {
        this.pauseViews()
    }

    @CallSuper
    override fun onResume() {
        this.resumeViews()
    }

    @CallSuper
    override fun onStart() {
        this.startViews()
    }

    @CallSuper
    override fun onStop() {
        this.stopViews()
    }

    // TODO: Add documentation
    protected open fun registerViews() {}

    /**
     * Add given view in this container.
     *
     * @param view View to add
     *
     * @since 1.0.0
     *
     * @see LifecycleView
     */
    protected fun addView(view: LifecycleView) =
        this.views?.let { views: MutableList<LifecycleView> ->
            if (!views.contains(view)) views.add(view)
        }

    private fun createViews() {
        this.views = mutableListOf()
        this.registerViews()
        this.views?.forEach { view: LifecycleView -> view.onCreate() }
    }

    private fun destroyViews() {
        this.views?.apply {
            this.forEach { view: LifecycleView -> view.onDestroy() }
            this.clear()
        }
        this.views = null
    }

    private fun pauseViews() = this.views?.forEach { view: LifecycleView -> view.onPause() }

    private fun resumeViews() = this.views?.forEach { view: LifecycleView -> view.onResume() }

    private fun startViews() = this.views?.forEach { view: LifecycleView -> view.onStart() }

    private fun stopViews() = this.views?.forEach { view: LifecycleView -> view.onStop() }
}